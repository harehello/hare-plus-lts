package org.hare.core.sys.service;

import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.vo.SysRoleVO;
import org.hare.framework.jpa.BaseService;

import java.util.Set;

/**
 * @author wangcheng
 */
public interface SysRoleService extends BaseService<SysRoleDO, Long> {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysRoleVO findVoById(Long id);

    /**
     * 保存角色
     *
     * @param dto
     * @return
     */
    SysRoleDO save(SysRoleDTO dto);

    /**
     * 保存角色所有菜单权限
     *
     * @param dto
     * @return
     */
    SysRoleDO updateMenu(SysRoleDTO dto);

    /**
     * 获取角色菜单权限
     * 只返回最小一级的菜单id集合
     * @param roleId
     * @return
     */
    Set<Long> getMenuIds(Long roleId);

    /**
     * 保存角色数据权限
     *
     * @param dto
     * @return
     */
    SysRoleDO updateDept(SysRoleDTO dto);

    /**
     * 获取角色数据权限
     * 只返回最小一级的部门id集合
     * @param roleId
     * @return
     */
    Set<Long> getDeptIds(Long roleId);
}
