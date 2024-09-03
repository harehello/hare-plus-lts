package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.common.domain.QueryRequest;
import org.hare.core.sys.dto.SysRoleDTO;
import org.hare.core.sys.dto.SysRoleQuery;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.vo.SysRoleVO;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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
    @PostMapping
    public R create(@Validated @RequestBody SysRoleDTO request) {
        service.save(request);
        return R.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(@Validated @RequestBody SysRoleDTO request) {
        service.save(request);
        return R.success();
    }

    /**
     * 查询角色拥有操作权限
     */
    @GetMapping("/menu/{roleId}")
    public R getMenu(@PathVariable Long roleId) {
        return R.success(service.getMenuIds(roleId));
    }

    /**
     * 修改操作权限
     */
    @PutMapping("/menu")
    public R menu(@Validated @RequestBody SysRoleDTO request) {
        service.updateMenu(request);
        return R.success();
    }

    /**
     * 查询角色拥有数据权限
     */
    @GetMapping("/dept/{roleId}")
    public R getDept(@PathVariable Long roleId) {
        return R.success(service.getDeptIds(roleId));
    }

    /**
     * 修改数据权限
     */
    @PutMapping("/dept")
    public R dept(@Validated @RequestBody SysRoleDTO request) {
        service.updateDept(request);
        return R.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
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
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysRoleVO vo = service.findVoById(id);
        return R.success(vo);
    }
}
