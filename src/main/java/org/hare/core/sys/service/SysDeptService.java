package org.hare.core.sys.service;

import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.vo.SysDeptVO;
import org.hare.framework.jpa.BaseService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SysDept
 *
 * @author wangcheng
 */
public interface SysDeptService extends BaseService<SysDeptDO, Long> {


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
}
