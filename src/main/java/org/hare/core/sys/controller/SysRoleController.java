package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.core.sys.dto.SysRoleQuery;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.vo.SysRoleVO;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * SysDept
 *
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/role")
public class SysRoleController extends BaseController {
    private final SysRoleService service;

    /**
     * 添加
     * @param request
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:create') or 'admin' == authentication.name")
    @PostMapping
    public R create(@Validated @RequestBody SysRoleDTO request) {
        service.save(request);
        return R.success();
    }

    /**
     * 修改
     */
    @PreAuthorize("hasAuthority('sys:role:update') or 'admin' == authentication.name")
    @PutMapping
    public R update(@Validated @RequestBody SysRoleDTO request) {
        service.save(request);
        return R.success();
    }

    /**
     * 查询角色拥有操作权限
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/menu/{roleId}")
    public R getMenu(@PathVariable Long roleId) {
        return R.success(service.getMenuIds(roleId));
    }

    /**
     * 修改操作权限
     */
    @PreAuthorize("hasAuthority('sys:role:update') or 'admin' == authentication.name")
    @PutMapping("/menu")
    public R menu(@Validated @RequestBody SysRoleDTO request) {
        service.updateMenu(request);
        return R.success();
    }

    /**
     * 查询角色拥有数据权限
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/dept/{roleId}")
    public R getDept(@PathVariable Long roleId) {
        return R.success(service.getDeptIds(roleId));
    }

    /**
     * 修改数据权限
     */
    @PreAuthorize("hasAuthority('sys:role:update') or 'admin' == authentication.name")
    @PutMapping("/dept")
    public R dept(@Validated @RequestBody SysRoleDTO request) {
        service.updateDept(request);
        return R.success();
    }

    /**
     * 删除
     * 管理员角色不可删除（id：1）
     * @param id
     * @return
     */
    @PreAuthorize("(hasAuthority('sys:role:delete') or 'admin' == authentication.name) and 1 != #id")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable Long id) {
        service.deleteById(id);
        return R.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping
    public R delete(@RequestBody Long[] ids) {
        service.deleteAllById(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 分页列表
     * @param query
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/page")
    public R page(SysRoleQuery query) {
        Page<SysRoleDO> page = service.findAll(specification(query), query.getPageable());
        return R.success(page);
    }

    /**
     * 列表
     * @param query
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/list")
    public R list(SysRoleQuery query) {
        List<SysRoleDO> list = service.findAll(specification(query));
        return R.success(list);
    }

    private Specification<SysRoleDO> specification(SysRoleQuery query) {
        return new HareSpecification<SysRoleDO>()
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .asc("seq");
    }

    /**
     * 详情
     * @return
     */
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysRoleVO vo = service.findVoById(id);
        return R.success(vo);
    }
}
