package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.repository.SysMenuRepository;
import org.hare.core.sys.service.SysMenuService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单服务实现类
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl implements SysMenuService {


    private final SysMenuRepository repository;

    /**
     * 创建
     * @param form
     * @return
     */
    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public SysMenuDO create(SysMenuDO form) {
        return repository.save(form);
    }

    /**
     * 查询用户菜单，系统管理员账号查询全部
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuDO> findMenuByUserId(Long userId) {
        return repository.findAll(new HareSpecification<SysMenuDO>()
                .eq(!Objects.equals(SysConstants.USER_SYSTEM_ID, userId),"roles.users", userId)
                .eq("type", SysConstants.MENU_TYPE_MENU)
                .distinct()
                .asc("seq"));
    }

    /**
     * 查询用户权限，系统管理员账号查询全部
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuDO> findBtnByUserId(Long userId) {
        List<SysMenuDO> list = repository.findAll(new HareSpecification<SysMenuDO>()
                .eq(!Objects.equals(SysConstants.USER_SYSTEM_ID, userId),"roles.users", userId)
                .eq("type", SysConstants.MENU_TYPE_BUTTON)
                .distinct()
                .asc("seq"));

        return list.stream()
                .filter(m -> StringUtils.hasText(m.getPermissions()))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void deleteById(Long id) {
        repository.deleteById( id);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void deleteAllById(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    @Cacheable(value = "menus", key = "'tree' + #type + #name")
    public List<SysMenuDO> findList(Integer type, String name) {
        Specification<SysMenuDO> specification = new HareSpecification<SysMenuDO>()
                .like(StringUtils.hasText(name), "name", name)
                .eq(Objects.nonNull(type), "type", type)
                .asc("seq");
        return repository.findAll( specification);
    }



}
