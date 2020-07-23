package com.wxss.kps.admin.aop;

import com.wxss.exception.CoreException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class HttpAop {
    private Logger logger = LoggerFactory.getLogger(HttpAop.class);

    /**
     * 不分修饰符和参数个数
     */
    @Pointcut("execution(public * com.wxss..*.controller..*.*(..))")
    public void httpLog() {}

//    @Pointcut("execution(public * org.esbuilder.business.rbac.action..*.*(..))")
//    public void rbacActionLog() {
//    }

//    @Before("httpLog() || rbacActionLog()")
    @Before("httpLog()")
    public void requestLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sb = "\n请求开始..." +
                "\nsessionID : " + request.getRequestedSessionId() +
                "\nURL : " + request.getRequestURL().toString() +
                "\nHOST : " + request.getRemoteHost() +
                "\nHTTP_METHOD : " + request.getMethod() +
                "\nIP : " + request.getRemoteAddr() +
                "\nCLASS_METHOD : " + "joinPoint.getSignature().getDeclaringTypeName()" + "." + joinPoint.getSignature().getName() +
                "\nARGS : " + Arrays.toString(joinPoint.getArgs());
        logger.info(sb);

    }

//    @AfterReturning(returning = "response", pointcut = "httpLog() || rbacActionLog()")
    @AfterReturning(returning = "response", pointcut = "httpLog()")
    public void afterReturning(Object response) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String sb = "\n请求成功..." +
                "\nsessionID : " + request.getRequestedSessionId() +
                "\nURL : " + request.getRequestURL().toString() +
                "\n响应参数 : " + response;
        logger.info(sb);
    }

//    @AfterThrowing(pointcut = "httpLog() || rbacActionLog()", throwing = "e")
    @AfterThrowing(pointcut = "httpLog()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String sb = "\n请求失败..." +
                "\nsessionID : " + request.getRequestedSessionId() +
                "\nURL : " + request.getRequestURL().toString() +
                "\n异常类型 : " + e.getClass().getTypeName() +
                "\n异常信息 : " + e.getMessage();
        logger.error(sb);
        if (!(e instanceof CoreException)) {
            logger.error("详细信息:", e);
        }
    }
}
