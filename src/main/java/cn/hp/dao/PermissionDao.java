package cn.hp.dao;

import cn.hp.domain.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findAll();

    List<Permission> findAllParent();

    void save(Permission permission);
}
