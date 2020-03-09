package cn.hp.service;

import cn.hp.domain.SysLog;

import java.util.List;

public interface LogService {

    void save(SysLog log);
    //查看
    List<SysLog> findAll();
}
