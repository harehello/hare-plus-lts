package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysDeptQuery;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.service.SysDeptService;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 部门控制
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/dept")
public class SysDeptController extends BaseController {
    private final SysDeptService service;

    /**
     * 添加
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:create')")
    @PostMapping
    public R create(@Validated @RequestBody SysDeptDO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:update')")
    @PutMapping
    public R update(@Validated @RequestBody SysDeptDO form) {
        service.update(form);
        return R.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:delete')")
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
    @PreAuthorize("hasAuthority('sys:dept:delete')")
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
    @PreAuthorize("hasAuthority('sys:dept:list')")
    @GetMapping("/page")
    public R page(SysDeptQuery query) {
        Page<SysDeptDO> page = service.findPage(query);
        return R.success(page);
    }

    /**
     * 详情
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:list')")
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysDeptDO entity = service.findById(id);
        return R.success(entity);
    }

    /**
     * 树形结构
     * @param query
     * @return
     */
    @GetMapping("/tree")
    public R list(SysDeptQuery query) {
        List<SysDeptDO> list = service.findList(query);
        return R.success(service.bulidTree(list));
    }
}
