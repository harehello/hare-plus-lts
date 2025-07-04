package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.service.SysMenuService;
import org.hare.framework.web.domain.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * SysDept
 *
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/menu")
public class SysMenuController extends BaseController {
    private final SysMenuService service;

    /**
     * 添加
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:create') or 'admin' == authentication.name")
    @PostMapping
    @CacheEvict(value = "menus", allEntries = true)
    public R create(@Validated @RequestBody SysMenuDO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:update') or 'admin' == authentication.name")
    @PutMapping
    @CacheEvict(value = "menus", allEntries = true)
    public R update(@Validated @RequestBody SysMenuDO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:delete') or 'admin' == authentication.name")
    @DeleteMapping("/{id}")
    @CacheEvict(value = "menus", allEntries = true)
    public R delete(@PathVariable Long id) {
        service.deleteById(id);
        return R.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('sys:menu:delete') or 'admin' == authentication.name")
    @DeleteMapping
    @CacheEvict(value = "menus", allEntries = true)
    public R delete(@RequestBody Long[] ids) {
        service.deleteAllById(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 树形结构
     * @param type
     * @return
     */
    @GetMapping("/tree")
    @Cacheable(value = "menus", key = "'tree' + #type + #name")
    public R list(Integer type, String name) {
        Specification<SysMenuDO> specification = new HareSpecification<SysMenuDO>()
                .like(StringUtils.hasText(name), "name", name)
                .eq(Objects.nonNull(type), "type", type)
                .asc("seq");
        List<SysMenuDO> list = service.findAll(specification);
        return R.success(service.bulidTree(list));
    }

}
