package cn.hp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色 实体类
 */
public class Role {

    private int id;
    private String roleName;
    private String roleDesc;

    //1.角色包含多个用户 ， 角色包含多个权限
    private List<SysUser> userList = new ArrayList<>();
    private List<Permission> permissionList = new ArrayList<>();

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
