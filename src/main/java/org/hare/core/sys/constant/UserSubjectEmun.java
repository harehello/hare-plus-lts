package org.hare.core.sys.constant;

/**
 * 用户类型
 * @author wang cheng
 */
public enum UserSubjectEmun {
    /**
     * 公司员工
     */
    employee("企业员工");

    private final String label;

    UserSubjectEmun(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
