package cn.hp.service;

import cn.hp.domain.Role;
import cn.hp.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {

    List<SysUser> findAll();

    void save(SysUser user);

    /**
     * 根据用户名查询当前用户是否存在
     * @param username
     * @return
     */
    boolean isUniqueUsername(String username);

    /**
     * 根据用户id查询 当前用户
     * @param id
     * @return
     */
    SysUser findUserById(Integer id);
    /**
     * 根据用户id查询 当前用户下所属的角色和 权限
     * @param id
     * @return
     */
    List<Role> findRoleAndPermission(Integer id);
    /**
     * 根据用户id查询 当前用户已具备的角色
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(Integer id);

    /**
     * 保存角色
     * @param id        当前用户id
     * @param ids       当前用户已经拥有角色
     */
    void addRoleToUser(Integer id, Integer[] ids);
}
