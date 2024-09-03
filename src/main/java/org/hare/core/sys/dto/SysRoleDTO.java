package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysRoleDO;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色数据传输对象
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysRoleDTO implements Serializable {
    private static final long serialVersionUID = 6429708510102264758L;

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

    /**
     * 部门id
     */
    private List<Long> deptIds;

    public static SysRoleDO convert(SysRoleDTO bodyDTO) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(bodyDTO.getId());
        sysRoleDO.setName(bodyDTO.getName());
        sysRoleDO.setSeq(bodyDTO.getSeq());
        sysRoleDO.setDataScope(bodyDTO.getDataScope());
        sysRoleDO.setRemark(bodyDTO.getRemark());
        return sysRoleDO;

    }
}
