package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysDictItemQuery;
import org.hare.core.sys.model.SysDictItemDO;
import org.hare.core.sys.service.SysDictItemService;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * 字典明细
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/dictItem")
public class SysDictItemController extends BaseController {
    private final SysDictItemService service;


    /**
     * 添加
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:create')")
    @PostMapping
    public R create(@Validated @RequestBody SysDictItemDO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:update')")
    @PutMapping
    public R update(@Validated @RequestBody SysDictItemDO form) {
        service.update(form);
        return R.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:delete')")
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
    @PreAuthorize("hasAuthority('sys:dict:delete')")
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
    @PreAuthorize("hasAuthority('sys:dict:list')")
    @GetMapping("/page")
    public R page(SysDictItemQuery query) {
        Page<SysDictItemDO> page = service.findPage(query);
        return R.success(page);
    }

    /**
     * 详情
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:list')")
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysDictItemDO entity = service.findById(id);
        return R.success(entity);
    }
}
