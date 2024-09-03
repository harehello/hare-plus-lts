package org.hare.core.sys.model;
import lombok.Getter;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 字典明细
* @author wang cheng
*/
@Getter
@Setter
@Entity
@Table(name="sys_dict_item")
public class SysDictItemDO extends BaseEntity {

    private static final long serialVersionUID = -824928080090961036L;

    @ManyToOne
    private SysDictDO dict;

    /**
     * 标签
     */
    private String label;

    /**
     * 值
     */
    private String value;
    /** 排序 **/
    private Integer seq;
}