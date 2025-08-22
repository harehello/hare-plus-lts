package org.hare.framework.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wang cheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataIsolator {

    /**
     * 部门定义的别名
     */
    String deptAlias() default "deptId";

    /**
     * 用户别名
     * @return
     */
    String userAlias() default "createBy";
}
