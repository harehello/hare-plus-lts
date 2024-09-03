package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.common.domain.QueryRequest;
import org.hare.core.sys.model.SysDictItemDO;
import org.hare.core.sys.service.SysDictItemService;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
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
    @PostMapping
    public R create(@Validated @RequestBody SysDictItemDO form) {
        service.save(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PutMapping
    public R update(@Validated @RequestBody SysDictItemDO form) {
        service.save(form);
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
    public R page(QueryRequest query, @RequestParam("dictId") Long dictId) {
        Specification<SysDictItemDO> specification = new HareSpecification<SysDictItemDO>()
                .eq("dict", dictId)
                .asc("seq");
        Page<SysDictItemDO> page = service.findAll(specification, query.getPageable());
        return R.success(page);
    }

    /**
     * 详情
     * @return
     */
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysDictItemDO entity = service.findById(id);
        return R.success(entity);
    }
}
