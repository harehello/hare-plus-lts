package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysDictQuery;
import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.service.SysDictService;
import org.hare.core.sys.vo.SysDictVO;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 字典管理
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/dict")
public class SysDictController extends BaseController {
    private final SysDictService service;

    /**
     * 添加
     * @param form
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:create')")
    @PostMapping
    public R create(@Validated @RequestBody SysDictDO form) {
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
    public R update(@Validated @RequestBody SysDictDO form) {
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
    public R page(SysDictQuery query, @RequestParam(value = "name", required = false) String name) {
        Page<SysDictDO> page = service.findPage(query);
        return R.success(page);
    }

    /**
     * 列表
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:list')")
    @GetMapping("/list")
    public R list() {
        List<SysDictVO> all = service.findVo();
        return R.success(all);
    }
}
