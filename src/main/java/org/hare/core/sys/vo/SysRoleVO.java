package org.hare.core.sys.vo;

import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysRoleDO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色视图
 * @author wang cheng
 */
@Getter
@Setter
public class SysRoleVO {

    private Long id;
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
     * 自定义 diy
     * 无 none
     */
    private String dataScope;
    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单id
     */
    private List<Long> menuIds;

    public SysRoleVO(SysRoleDO roleDO) {
        this.id = roleDO.getId();
        this.name = roleDO.getName();
        this.seq = roleDO.getSeq();
        this.dataScope = roleDO.getDataScope();
        this.remark = roleDO.getRemark();
        this.menuIds = roleDO.getMenus().stream().map(SysMenuDO::getId).collect(Collectors.toList());
    }
}
