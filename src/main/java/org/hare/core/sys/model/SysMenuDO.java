package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 菜单
 * @author wang cheng
 */
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="sys_menu")
public class SysMenuDO implements Serializable {
    private static final long serialVersionUID = 1627530366090989006L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     * 路由地址
     */
    private String url;
    /**
     * 打开方式
     */
    private String target;
    /**
     * 权限码
     */
    private String permissions;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer seq;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    private Set<SysRoleDO> roles;

    public SysMenuDO() {
    }

    public SysMenuDO(Long id) {
        this.id = id;
    }
}
