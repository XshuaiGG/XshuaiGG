package cn.hp.service.imp;

import cn.hp.dao.UserDao;
import cn.hp.domain.Role;
import cn.hp.domain.SysUser;
import cn.hp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    //通过注解拿到加密对象
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1. 根据用户名查询一个 sysUser 对象
        SysUser sysUser = userDao.findByUserName(username);
        if(sysUser!=null) {
            //授权/角色集合
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //创建一个授权-->角色对象
            //GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            //authorities.add(authority);

            //TODO 从sysUser中获取到 查询出来的 所有角色
            for(Role role:sysUser.getRoleList()){
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoleName());
                authorities.add(authority);
            }
            //参数1: 用户名 ; 参数2: 密码 (他们都来源于数据库)
            //参数3: 授权集合
            User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
            return user;
        }
        return null;
    }

    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    /**
     * TODO 做一个代码改进(进入加密机制) 主要是密码加密
     * 最常用的密码加密手段 :  md5  ,  base64 , sha1 值  ...
     *
     * @param user
     */
    @Override
    public void save(SysUser user) {

       /*1.  md5的加密方式
        //如果使用md5 对密码加密 怎么保证新增用户时,数据库的密码是加密的
        String oldPass = user.getPassword();    //明文密码
        String newPass = MD5Utils.md5(oldPass+"houpu"); //加密后密码
        user.setPassword(newPass);              //将md5加密后的密文设置到 原有user对象中
        //如果程序中使用MD5加密 , 加盐操作  ,salt 对密码第2次加密
        //如果使用md5 加密方式 , 在登陆前 ,将输入的密码 先通过MD5加密一次 .程序拿着加密后的密码,执行登陆*/


        //2.使用安全框架的加密方式
        String encode = passwordEncoder.encode(user.getPassword()); //拿到加密后的密码
        user.setPassword(encode);//将加密后的密文设置到 原有user对象中
        userDao.save(user);

    }

    //检查用户是否唯一
    @Override
    public boolean isUniqueUsername(String username) {
        SysUser sysUser = userDao.isUniqueUsername(username);
       /* if(sysUser==null){
            return true;
        }else{
            return false;
        }*/

       return sysUser==null;
    }

    @Override
    public SysUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<Role> findRoleAndPermission(Integer id) {
        return userDao.findRoleAndPermission(id);
    }

    @Override
    public List<Role> findRoleByUserId(Integer id) {
        return userDao.findRoleByUserId(id);
    }

    @Override
    public void addRoleToUser(Integer id, Integer[] ids) {

        //1. 清空当前用户已经拥有角色 （避免重复添加角色 导致报错）
        userDao.delete(id); //根据用户id清空当前用户所有的角色

        //2. 判断 传递的ids (重新勾选的ids是否存在)
        if(ids!=null){
            for(Integer rid:ids){
                Map<String,Object> map = new HashMap<>();
                map.put("id",id);       //当前用户id
                map.put("rid",rid);     //勾选的角色id
                userDao.addRoleToUser(map);
            }
        }
    }
}
