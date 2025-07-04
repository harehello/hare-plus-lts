package org.hare.core.sys.constant;

/**
 * 用户数据权限类型
 * @author wang cheng
 */
public enum UserDataScopeEmun {
    /**
     * 全部
     */
    all,
    /**
     * 所有上级部门
     */
    parent,
    /**
     * 所有下级部门
     */
    sub,
    /**
     * 本部门
     */
    dept,
    /**
     * 自己
     */
    self,
    /**
     * 自定义
     */
    custom,
    /**
     * 无
     */
    none
}
