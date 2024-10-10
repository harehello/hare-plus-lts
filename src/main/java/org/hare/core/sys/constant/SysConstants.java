package org.hare.core.sys.constant;

import org.hare.common.constant.Constants;
import org.hare.common.domain.OptionResponse;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 常量
 * @author wang cheng
 */
public final class SysConstants {

    /**
     * 用户信息
     */
    public static final String USER = "user";

    public static final String LOGIN_USER_PREFIX = "login:user:";
    /**
     * 用户默认密码
     */
    public static final String USER_DEFAULT_PASSWORD = "123456";
    /**
     * 用户信息不可被修改
     */
    public static final String USER_CANNOT_UPDATE_MSG = "用户信息不可被修改";
    public static final String USER_RAW_PASSWORD_ERROR_MSG = "原密码不正确";
    public static final String USER_UNIQUE_ERROR_MSG = "用户已存在";
    /**
     * 登录错误消息
     */
    public static final String LOGIN_ERROR_MSG = "用户名或密码错误";

    /**
     * 系统管理员账号ID
     */
    public static final long USER_SYSTEM_ID = 1;

    /**
     * 菜单最顶级的ID
     */
    public static final long MENU_PARENT_TOP_ID = 0;

    /**
     * 菜单类型 菜单
     */
    public static final int MENU_TYPE_MENU = 10;
    /**
     * 菜单类型 按钮
     */
    public static final int MENU_TYPE_BUTTON = 20;

    /**
     * 最顶级的部门
     */
    public static final long DEPT_PARENT_TOP = 0;
    /**
     * 部门之间分割符
     */
    public static final String DEPT_PARENT_SEPARATOR = ",";

    /**
     * 系统管理员
     */
    public static final Long ROLE_ADMIN = 1L;
    /**
     * 企业管理员
     */
    public static final Long ROLE_10 = 10L;
    /**
     * 部门负责人
     */
    public static final Long ROLE_20 = 20L;

    /**
     * 用户角色
     */
    public enum Role {

        /**
         * 系统管理员
         */
        admin("管理员"),
        /**
         * 普通用户
         */
        ordinary("普通用户");

        private String name;

        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static Map<Constants.Role, String> getMap() {
            return Arrays.stream(Constants.Role.values())
                    .collect(Collectors.toMap(v -> v, Constants.Role::getName));
        }

        public static Set<OptionResponse> getList() {
            return Arrays.stream(Constants.Role.values())
                    .map(v -> new OptionResponse(v.name(), v.getName()))
                    .collect(Collectors.toSet());
        }
    }
}
