package org.hare.common.constant;

/**
 * 删除状态枚举
 * @author wang cheng
 */
public enum DeleteEmun {
    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private final int code;
    private final String desc;

    DeleteEmun(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
