package cn.hp.dao;

import cn.hp.domain.SysLog;

import java.util.List;

public interface LogDao {
    void save(SysLog log);
    //查看
    List<SysLog> findAll();
}
