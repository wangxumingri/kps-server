

## 日志

```
logging:
  level:
    root: info
    # 只打印sql语句
    com.wss.kps.dao: debug
  file:
    name: D:/log/kps.log
```

**使用示例**

```
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    ...
    
    logger.error("处理参数校验异常:{}",e.getMessage(),e);
```

## 配置AOP

添加依赖
```
 <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>${aspectj.version}</version>
 </dependency>
```

配置切面
```
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

```

## 跨域处理

### 使用`@CrossOrigin`注解

在需要跨域的接口上使用`@CrossOrigin`注解，（麻烦）

```
@Controller
@RequestMapping("")
@CrossOrigin
public class LoginController {

    @Autowired
    private IRbacService rbacService;

    @RequestMapping("login")
    @ResponseBody
    public ResponseVo<LoginResDto> login(@Validated @RequestBody RequestVo<LoginReqDto> requestVo) throws ServiceException {
        return ResponseBuilder.success(rbacService.login(requestVo.getBody())) ;
    }

}
```

### 全局配置WebMvcConfigurer

```
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT", "HEAD")
                .allowCredentials(true);
    }

<!--    @Override-->
<!--    public void addResourceHandlers(ResourceHandlerRegistry registry) {-->
<!--        registry.addResourceHandler("/static/**")-->
<!--                .addResourceLocations("resources:/static/")-->
<!--                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());-->
<!--    }-->

}
```