package cn.hp.dao;

import cn.hp.domain.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> findAll();

    void save(Role role);
}
