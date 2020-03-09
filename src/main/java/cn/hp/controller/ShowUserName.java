package cn.hp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * 从安全框架登陆成功后 ,将用户名显示到 页面
 */
@Controller
public class ShowUserName {

    /**
     * 显示用户名 ( 用户登陆成功后原来不使用安全框架的做法是,自行将用户对象存入session,并且在需要的地方取出)
     * 使用了 安全框架后(存入session的操作 已经被 spring-security完成了 ,我们要做的事情: 取出session中的用户名)
     */
    @RequestMapping("/show")
    public void showUsername(HttpServletRequest request){
        //TODO 方式1的写法
        HttpSession session = request.getSession();

        //获取当前session中所有key值,
        Enumeration attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            Object o = attributeNames.nextElement();
            System.out.println(o);
        }
        //得到一个 安全框架上下文对象 (得到安全框架的环境对象)
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        //通过环境对象拿到一个身份认证对象
        Authentication authentication = securityContext.getAuthentication();
        //获取用户的信息  (安全框架的User对象 --  UserDetails对象)
        User user = (User) authentication.getPrincipal();
        //拿到用户名
        String username = user.getUsername();
        System.out.println(username);

        //方式2写法 :  TODO  简写方式
        SecurityContext context = SecurityContextHolder.getContext();
    }
}













