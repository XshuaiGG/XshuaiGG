package cn.hp.controller;

import cn.hp.domain.SysLog;
import cn.hp.domain.SysUser;
import cn.hp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*日志表现层
* */
@Controller
@RequestMapping("/sysLog")
//@Secured({"ROLE_ADMIN"})
public class LogController {
    @Autowired
    private LogService logService;
//查找
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView model){
        List<SysLog> sysLogList = logService.findAll();
        model.addObject("sysLogList",sysLogList);
        model.setViewName("sysLog-list");
        return model;
    }
}
