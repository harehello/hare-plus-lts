package org.hare.core.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色
 * @author wang cheng
 */
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "sys_role")
public class SysRoleDO extends BaseEntity {
    private static final long serialVersionUID = 7092776399090792103L;

    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer seq;

    /**
     * 数据权限范围
     * 全部 all
     * 所有上级部门 parent
     * 所有下级部门 sub
     * 本部门 dept
     * 自己 self
     * 自定义 custom
     * 无 none
     */
    private String dataScope;
    /**
     * 备注
     */
    private String remark;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<SysUserDO> users;
    /**
     * 菜单
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sys_role_menu",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private Set<SysMenuDO> menus;

    /**
     * 部门
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "sys_role_dept",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id",referencedColumnName = "id")})
    private Set<SysDeptDO> depts;
}