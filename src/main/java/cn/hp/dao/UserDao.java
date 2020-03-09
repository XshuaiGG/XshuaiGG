package cn.hp.dao;

import cn.hp.domain.Role;
import cn.hp.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * 根据用户名查询一个 SysUser 对象
     * @param username
     * @return
     */
    public SysUser findByUserName(String username);

    List<SysUser> findAll();

    void save(SysUser user);

    /**
     * 根据用户名查询一个用户对象
     * @param username
     * @return
     */
    SysUser isUniqueUsername(String username);

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
     * 根据用户id，删除当前用户所拥有的 所有角色
     * @param id
     */
    void delete(Integer id);

    /**
     * 保存某个用户勾选的角色信息
     * @param map
     */
    void addRoleToUser(Map<String, Object> map);
}
