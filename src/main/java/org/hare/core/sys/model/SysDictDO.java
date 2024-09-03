package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 	数据字典
 * @author wang cheng
 */
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="sys_dict")
public class SysDictDO extends BaseEntity {
	private static final long serialVersionUID = -7729594457310473923L;
	/** 名称 **/
	private String name;
	/** 编码 **/
	@Column(unique = true)
	private String code;
	/** 排序 **/
	private Integer seq;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 字典明细
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "dict", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private List<SysDictItemDO> items;
}
