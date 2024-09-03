package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门/组织架构
 * @author wang cheng
 */
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="sys_dept")
public class SysDeptDO extends BaseEntity {
    private static final long serialVersionUID = 7189913276809710125L;

    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    @Column(unique = true)
    private String sn;
    /**
     * 层级
     */
    @Column(columnDefinition = "tinyint")
    private Integer level;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 介绍
     */
    private String remark;
    /**
     * 父级ID
     */
    private Long pid;
    /**
     * 快速识别码
     */
    @JsonIgnore
    private String fastId;

    public SysDeptDO(Long id) {
        this.id = id;
    }
}
