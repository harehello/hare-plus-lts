package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.common.component.CrudService;
import org.hare.common.constant.DeleteEmun;
import org.hare.core.sys.dto.SysDeptQuery;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.vo.SysDeptVO;
import org.hare.framework.aop.DataIsolator;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门服务
 * @author wangcheng
 */
public interface SysDeptService extends CrudService<SysDeptDO, Long, SysDeptQuery> {


    /**
     * 构建树结构
     * @param list
     * @return
     */
    default LinkedHashSet<SysDeptVO> bulidTree(List<SysDeptDO> list) {
        LinkedHashSet<SysDeptVO> tree = new LinkedHashSet<>();
        if (list.isEmpty()) {
            return tree;
        }
        // 构建视图
        final List<SysDeptVO> vos = list.stream().map(SysDeptVO::new).collect(Collectors.toList());
        // 获取顶级
        Integer topLevel = vos.stream().map(SysDeptVO::getLevel).min((Comparator.comparingInt(o -> o))).get();
        // 根据pid分组
        final Map<Long, List<SysDeptVO>> map = vos.stream().collect(Collectors.groupingBy(SysDeptVO::getPid));
        // 为每个添加子集
        vos.forEach(v -> v.setChildren(map.get(v.getId())));
        // 筛选顶级部门
        tree = vos.stream().filter(v -> Objects.equals(topLevel, v.getLevel()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return tree;
    }

    /**
     * 获取快速识别码
     * @param id 部门ID
     * @return
     */
    default String getFastId(Long id) {
        final SysDeptDO deptDO = findById(id);
        return Objects.isNull(deptDO) ? null : deptDO.getFastId()+ "-";
    }

    default Specification<SysDeptDO> specification(SysDeptQuery query) {
        return new HareSpecification<SysDeptDO>()
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .eq("deleted", DeleteEmun.NOT_DELETED.getCode())
                .asc("seq");
    }

    /**
     * 创建
     * @param entity
     * @return
     */
    SysDeptDO create(SysDeptDO entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    SysDeptDO update(SysDeptDO entity);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    SysDeptDO findById(Long id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteAllById(List<Long> ids);

    /**
     * 查询分页列表
     * @param query
     * @return
     */
    Page<SysDeptDO> findPage(SysDeptQuery query);
    /**
     * 查询列表
     * @param query
     * @return
     */
    List<SysDeptDO> findList(SysDeptQuery query);
}
