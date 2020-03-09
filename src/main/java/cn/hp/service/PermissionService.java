package cn.hp.service;

import cn.hp.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> findAllParent();

    void save(Permission permission);
}
