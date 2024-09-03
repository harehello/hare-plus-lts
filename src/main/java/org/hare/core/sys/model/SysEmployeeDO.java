package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 员工
 * @author wang cheng
 */
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="sys_employee", indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "userId")
}
)
public class SysEmployeeDO extends BaseEntity {
    private static final long serialVersionUID = 5070934340230273030L;

    /**
     * 部门
     */
    @ManyToOne
    private SysDeptDO dept;
    /**
     * 姓名
     */
    private String name;
    /**
     * 人员编号
     */
    private String code;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    @Temporal(TemporalType.DATE)
    private Date birthday;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 岗位
     */
    @ManyToOne
    private SysJobDO job;
    /**
     * 入职日期
     */
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户ID
     */
    private Long userId;

}
