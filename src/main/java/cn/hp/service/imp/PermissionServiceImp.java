package cn.hp.service.imp;

import cn.hp.dao.PermissionDao;
import cn.hp.domain.Permission;
import cn.hp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> findAllParent() {
        return permissionDao.findAllParent();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
