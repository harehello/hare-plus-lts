package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysJobDTO;
import org.hare.core.sys.dto.SysJobQuery;
import org.hare.core.sys.model.SysJobDO;
import org.hare.core.sys.service.SysJobService;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位管理控制
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/job")
public class SysJobController extends BaseController {
    private final SysJobService service;


    /**
     * 添加
     * @param request
     * @return
     */
    @PostMapping
    public R create(@Validated @RequestBody SysJobDTO request) {
        service.save(SysJobDTO.convert(request));
        return R.success();
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @PutMapping
    public R update(@Validated @RequestBody SysJobDTO request) {
        service.save(SysJobDTO.convert(request));
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
    public R page(SysJobQuery query) {
        Page<SysJobDO> page = service.findAll(service.specification(query), query.getPageable());
        return R.success(page);
    }

    /**
     * 列表
     * @param query
     * @return
     */
    @GetMapping("/list")
    public R list(SysJobQuery query) {
        List<SysJobDO> list = service.findAll(service.specification(query));
        return R.success(list);
    }

    /**
     * 详情
     * @return
     */
    @GetMapping("/{id}")
    public R info(@PathVariable Long id) {
        SysJobDO entity = service.findById(id);
        return R.success(entity);
    }
}
