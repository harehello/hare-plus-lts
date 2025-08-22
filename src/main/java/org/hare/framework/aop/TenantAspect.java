package org.hare.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.hare.common.domain.QueryRequest;
import org.hare.framework.security.SecurityContextUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author wang cheng
 */
@Aspect
@Component
public class TenantAspect {

    // 定义切入点：所有 controller 包下的方法
    @Pointcut("@annotation(org.hare.framework.aop.DataIsolator)")
    public void logExecution() {}

    // 前置通知
    @Before("logExecution()")
    public void beforeMethod(JoinPoint joinPoint) {
    }

    // 后置通知
    @After("logExecution()")
    public void afterMethod(JoinPoint joinPoint) {
    }

    // 返回通知
    @AfterReturning(pointcut = "logExecution()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
    }

    // 异常通知
    @AfterThrowing(pointcut = "logExecution()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        ex.printStackTrace();
    }

    // 环绕通知
    @Around("logExecution()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataIsolator dataFilter = signature.getMethod().getAnnotation(DataIsolator.class);
        //获取别名
        String deptAlias = dataFilter.deptAlias();
        String userAlias = dataFilter.userAlias();

        SecurityContextUtils.getLoginUser();

        for (Object arg : args) {
            if (arg instanceof Specification) {
                // 获取可访问部门
                arg = ((Specification<?>) arg).and((root, query, c)
                        -> c.and(c.equal(root.get("id"), 1)));
                break;
            } else if (arg instanceof QueryRequest) {

                break;
            }
        }
        // 执行原方法
        Object proceed = joinPoint.proceed(args);
        if (proceed instanceof Specification) {
            // 获取可访问部门
            proceed = ((Specification<?>) proceed).and((root, query, c)
                    -> c.and(c.equal(root.get("id"), 1)));
        }
        return proceed;
    }
}
