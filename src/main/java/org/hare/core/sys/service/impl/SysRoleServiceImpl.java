package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.core.sys.dto.SysRoleQuery;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.repository.SysRoleRepository;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.vo.SysRoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleRepository repository;

    @Transactional(readOnly = true)
    @Override
    public SysRoleVO findVoById(Long id) {
        SysRoleDO roleDO = findById(id);
        return new SysRoleVO(roleDO);
    }

    /**
     * 保存角色
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO save(SysRoleDTO dto) {
        final SysRoleDO target = SysRoleDTO.convert(dto);
        return repository.save(target);
    }

    /**
     * 更新角色菜单
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO updateMenu(SysRoleDTO dto) {
        SysRoleDO roleDO = findById(dto.getId());
        final List<Long> menuIds = dto.getMenuIds();
        if (CollectionUtils.isEmpty(menuIds)) {
            roleDO.setMenus(Collections.emptySet());
        } else {
            roleDO.setMenus(menuIds.stream().map(SysMenuDO::new).collect(Collectors.toSet()));
        }
        return repository.save(roleDO);
    }

    /**
     * 获取角色菜单权限
     * 只返回最小一级的菜单id集合
     *
     * @param roleId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Set<Long> getMenuIds(Long roleId) {
        SysRoleDO roleDO = findById(roleId);
        final Set<SysMenuDO> menus = roleDO.getMenus();
        final Set<Long> ids = menus.stream().map(SysMenuDO::getId).collect(Collectors.toSet());
        final Set<Long> pids = menus.stream().map(SysMenuDO::getPid).collect(Collectors.toSet());
        ids.removeAll(pids);
        return ids;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysRoleDO updateDept(SysRoleDTO dto) {
        SysRoleDO roleDO = findById(dto.getId());
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

        return repository.save(roleDO);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Long> getDeptIds(Long roleId) {
        SysRoleDO roleDO = repository.findById(roleId).get();
        final Set<SysDeptDO> depts = roleDO.getDepts();
        final Set<Long> ids = depts.stream().map(SysDeptDO::getId).collect(Collectors.toSet());
        final Set<Long> pids = depts.stream().map(SysDeptDO::getPid).collect(Collectors.toSet());
        ids.removeAll(pids);
        return ids;
    }

    @Override
    public SysRoleDO create(SysRoleDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysRoleDO update(SysRoleDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysRoleDO findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Page<SysRoleDO> findPage(SysRoleQuery query) {
        final Specification<SysRoleDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysRoleDO> findList(SysRoleQuery query) {
        return repository.findAll(specification(query));
    }

    @Override
    public Specification<SysRoleDO> specification(SysRoleQuery query) {
        return new HareSpecification<SysRoleDO>()
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .asc("seq");
    }
}
