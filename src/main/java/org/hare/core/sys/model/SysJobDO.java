package org.hare.core.sys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 岗位
 * @author wang cheng
 */
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="sys_job")
public class SysJobDO extends BaseEntity {
    private static final long serialVersionUID = -7075390091090325686L;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    private String name;
    /**
     * 编码
     */
    @NotBlank(message = "岗位编码不能为空")
    private String code;
    /**
     * 排序
     */
    @NotNull(message = "岗位排序不能为空")
    private Integer seq;
    /**
     * 状态
     * 正常、停用
     * active inactive
     */
    @NotBlank(message = "岗位状态不能为空")
    private String status;

    /**
     * 作业方式
     */
    private String mode;
    /**
     * 作业时长
     * 小时/天
     */
    private Integer duration;

    public SysJobDO(Long id) {
        this.id = id;
    }
}

