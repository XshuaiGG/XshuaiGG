package cn.hp.log;

import cn.hp.domain.SysLog;
import cn.hp.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志切面类 （切入点和通知（又叫增强）的组合 ）
 *
 *  注解完成 切面类编写
 *      注意：1.当前类需交给spring容器 管理 （当前类创建对象由spring容器管理）
 *            2.因为是切面类，加入@Aspect
 */

@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request; //spring容器默认已经管理了HttpServletRequest

    @Autowired
    private LogService logService;

    /**
     * 环绕通知一般会有返回值 ，Object
     * @param joinPoint  参数用于 对目标执行环绕后，需提供一个返回值
     * @return
     */
    @Around(value="pointcut1()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //1. 创建日志对象
        SysLog log = new SysLog();
        //2. 将需要的数据封装到 日志对象
        log.setVisitTime(new Date());   //存入当前系统时间
        log.setIp(request.getRemoteAddr()); //将客户机ip存入log

        //可以从security上下文对象中得到用户名
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();   //拿到 用户对象重要信息
        log.setUsername(user.getUsername());    //得到用户名

        Object target = joinPoint.getTarget();  //拿到被增强目标类（就是所有xxxController类）对象
        String className = target.getClass().getName(); //拿到被增强类名字

        String methodName = joinPoint.getSignature().getName();//拿到 实际被增强方法的签名
        log.setMethod(className+"."+methodName);

        //3. 将日志存入到 数据库
        logService.save(log);

        //对目标 环绕增强后，得到的返回值
        Object proceed = joinPoint.proceed();
        //环绕后，只需要将当前proceed对象返回
        return proceed;
    }

    @Pointcut(value="execution(* cn.hp.controller.*.*(..))")
    private void pointcut1(){}
}


















