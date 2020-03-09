package cn.hp.service;

import cn.hp.domain.Role;

import java.util.List;

/**
 * 角色业务层
 */
public interface RoleService {
    List<Role> findAll();

    void save(Role role);
}
