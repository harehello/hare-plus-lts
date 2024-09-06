package org.hare.core.sys.controller;

import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.dto.SysEmployeeQuery;
import org.hare.core.sys.model.SysEmployeeDO;
import org.hare.core.sys.service.SysEmployeeService;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * SysEmployee
 *
 * @author wang cheng
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sys/employee")
public class SysEmployeeController extends BaseController {
    private final SysEmployeeService service;


    /**
     * 添加
     * @param form
     * @return
     */
    @PostMapping
    public R create( @RequestBody @Validated SysEmployeeDTO form) {
        service.create(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PutMapping
    public R update(@Validated @RequestBody SysEmployeeDTO form) {
        service.update(form);
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
    public R page(SysEmployeeQuery query) {
        Page<SysEmployeeDO> page = service.findAll(service.specification(query), query.getPageable());
        return R.success(page);
    }

    /**
     * 详情
     * @return
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable Long id) {
        SysEmployeeDTO dto = service.findDtoById(id);
        return R.success(dto);
    }

}
