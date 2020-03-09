package cn.hp.controller;

import cn.hp.domain.Role;
import cn.hp.domain.SysUser;
import cn.hp.service.RoleService;
import cn.hp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户表现层
 */
@Controller
@RequestMapping("/user")
@Secured({"ROLE_ADMIN"})        //指定当前 路径只能是 admin角色才能访问
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 将所有用户 存到 model并显示到页面
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public ModelAndView findAll(ModelAndView model){
        List<SysUser> userList = userService.findAll();
        model.addObject("userList",userList);
        model.setViewName("user-list");
        return model;
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    public String save(SysUser user){
        userService.save(user);
        return "redirect:findAll";
    }

    /**
     * 检查当前用户是否存在
     * @return
     */
    @RequestMapping("/isUniqueUsername")
    @ResponseBody   //该注解可以将一个字符串 通过流的方式 写入到 客户端(jsp页面)
    public String isUniqueUsername(String username){
        boolean result = userService.isUniqueUsername(username);
        return String.valueOf(result);
    }


    /**
     *  根据用户id 查询当前用户所属的 角色和 权限
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/details")
    public ModelAndView details(ModelAndView model,Integer id){
        SysUser user = userService.findUserById(id);
        model.addObject("user",user);

        //要想获取 某个用户下的 角色 已经 权限信息  ，可以根据用户id 查询 角色信息（角色中包含了权限）
        List<Role> roleList = userService.findRoleAndPermission(id);
        model.addObject("roleList",roleList);

        model.setViewName("user-show");
        return model;
    }

    /**
     * 添加用户角色前的操作
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/addRoleUI")
    public ModelAndView addRoleUI(ModelAndView model ,Integer id){
        //获取所有角色信息
        List<Role> allRole = roleService.findAll();

        //根据用户id 先查出当前用户之前已经具备的角色
        List<Role> roleList = userService.findRoleByUserId(id);

        //循环用户已经拥有的角色，将数据做格式转换
        StringBuffer sb = new StringBuffer();
        for(Role r:roleList){
            sb.append(",");
            sb.append(r.getId());
            sb.append(",");
            // ,1, ,2, ,10,
        }

        model.addObject("str",sb.toString());//将用户拥有角色id的转换数据返回到页面
        model.addObject("allRole",allRole);//将所有角色信息返回到页面
        model.addObject("id",id);   //将当前用户id也返回到页面
        model.setViewName("user-role-add");
        return model;
    }


    /**
     * 完成角色保存功能
     * @param id    当前用户id
     * @param ids   当前用户所拥有的所有角色id数组
     * @return
     */
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer id,Integer[] ids){

        //调用业务层执行保存
        userService.addRoleToUser(id,ids);
        return "redirect:findAll";
    }
}

















