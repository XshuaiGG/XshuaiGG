package cn.hp.service.imp;

import cn.hp.dao.LogDao;
import cn.hp.domain.SysLog;
import cn.hp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogServiceImp implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void save(SysLog log) {
       logDao.save(log);
    }
//查看
    @Override
    public List<SysLog> findAll() {
        return logDao.findAll();
    }
}
