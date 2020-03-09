package cn.hp.controller;

import cn.hp.domain.Role;
import cn.hp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色的表现层
 */
@Controller
@RequestMapping("/role")
@Secured({"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView model = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        model.addObject("roleList",roleList);
        model.setViewName("role-list");
        return model;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }
}



