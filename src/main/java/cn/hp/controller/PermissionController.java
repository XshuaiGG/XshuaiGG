package cn.hp.controller;

import cn.hp.domain.Permission;
import cn.hp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限表现层
 */
@Controller
@RequestMapping("/permission")
@Secured({"ROLE_ADMIN"})
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView model){
        List<Permission> roleList = permissionService.findAll();
        model.addObject("permissionList",roleList);
        model.setViewName("permission-list");
        return model;
    }

    /**
     * 功能,查询父权限列表 ,并反馈给 jsp页面
     * @return
     */
    @RequestMapping("/addUI")
    public ModelAndView addUI(ModelAndView model){

        List<Permission> permissionList = permissionService.findAllParent();
        model.addObject("permissionList",permissionList);
        model.setViewName("permission-add");
        return model;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }

}














