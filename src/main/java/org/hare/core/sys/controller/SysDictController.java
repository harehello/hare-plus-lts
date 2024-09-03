package org.hare.core.sys.controller;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.component.BaseController;
import org.hare.common.domain.QueryRequest;
import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.service.SysDictService;
import org.hare.core.sys.vo.SysDictVO;
import org.hare.framework.web.domain.R;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
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
    @PostMapping
    public R create(@Validated @RequestBody SysDictDO form) {
        service.save(form);
        return R.success();
    }

    /**
     * 修改
     * @param form
     * @return
     */
    @PutMapping
    public R update(@Validated @RequestBody SysDictDO form) {
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
    public R page(QueryRequest query, @RequestParam(value = "name", required = false) String name) {
        Specification<SysDictDO> specification = new HareSpecification<SysDictDO>()
                .eq(StringUtils.hasText(name), "name", name)
                .asc("seq");
        Page<SysDictDO> page = service.findAll(specification, query.getPageable());
        return R.success(page);
    }

    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public R list() {
        List<SysDictVO> all = service.findVo();
        return R.success(all);
    }
}
