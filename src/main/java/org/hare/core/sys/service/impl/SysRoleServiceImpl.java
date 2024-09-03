package org.hare.core.sys.service.impl;

import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.vo.SysRoleVO;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDO, Long> implements SysRoleService {


    @Transactional(readOnly = true)
    @Override
    public SysRoleVO findVoById(Long id) {
        SysRoleDO roleDO = super.findById(id);
        return new SysRoleVO(roleDO);
    }

    /**
     * 保存角色
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO save(SysRoleDTO dto) {
        final SysRoleDO target = SysRoleDTO.convert(dto);
        return super.save(target);
    }

    /**
     * 更新角色菜单
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO updateMenu(SysRoleDTO dto) {
        SysRoleDO roleDO = super.findById(dto.getId());
        final List<Long> menuIds = dto.getMenuIds();
        if (CollectionUtils.isEmpty(menuIds)) {
            roleDO.setMenus(Collections.emptySet());
        } else {
            roleDO.setMenus(menuIds.stream().map(SysMenuDO::new).collect(Collectors.toSet()));
        }
        return super.save(roleDO);
    }

    /**
     * 获取角色菜单权限
     * 只返回最小一级的菜单id集合
     * @param roleId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Set<Long> getMenuIds(Long roleId) {
        SysRoleDO roleDO = super.findById(roleId);
        final Set<SysMenuDO> menus = roleDO.getMenus();
        final Set<Long> ids = menus.stream().map(SysMenuDO::getId).collect(Collectors.toSet());
        final Set<Long> pids = menus.stream().map(SysMenuDO::getPid).collect(Collectors.toSet());
        ids.removeAll(pids);
        return ids;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO updateDept(SysRoleDTO dto) {
        SysRoleDO roleDO = super.findById(dto.getId());
        final String dataScope = dto.getDataScope();
        roleDO.setDataScope(dataScope);

        if ("custom".equals(dataScope)) {
            final List<Long> deptIds = dto.getDeptIds();
            if (CollectionUtils.isEmpty(deptIds)) {
                roleDO.setDepts(Collections.emptySet());
            } else {
                roleDO.setDepts(deptIds.stream().map(SysDeptDO::new).collect(Collectors.toSet()));
            }
        } else {
            roleDO.setDepts(Collections.emptySet());
        }

        return super.save(roleDO);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Long> getDeptIds(Long roleId) {
        SysRoleDO roleDO = super.findById(roleId);
        final Set<SysDeptDO> depts = roleDO.getDepts();
        final Set<Long> ids = depts.stream().map(SysDeptDO::getId).collect(Collectors.toSet());
        final Set<Long> pids = depts.stream().map(SysDeptDO::getPid).collect(Collectors.toSet());
        ids.removeAll(pids);
        return ids;
    }
}
