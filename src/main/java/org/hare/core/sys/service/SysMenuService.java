package org.hare.core.sys.service;

import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.vo.SysMenuVO;
import org.hare.framework.jpa.BaseService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单 服务
 * @author wangcheng
 */
public interface SysMenuService extends BaseService<SysMenuDO, Long> {

    /**
     * 创建菜单
     * @param form
     * @return
     */
    SysMenuDO create(SysMenuDO form);

    /**
     * 构建菜单树结构
     * @param list
     * @return
     */
    default LinkedHashSet<SysMenuVO> bulidTree(List<SysMenuDO> list) {
        // 构建菜单视图
        LinkedHashSet<SysMenuVO> vos = list.stream().map(SysMenuVO::new).collect(Collectors.toCollection(LinkedHashSet::new));
        // 根据pid分组
        Map<Long, List<SysMenuVO>> map = vos.stream().collect(Collectors.groupingBy(SysMenuVO::getPid));
        // 为每个菜单添加子集
        vos.forEach(v -> v.setChildren(map.get(v.getId())));
        // 筛选一级菜单
        vos = vos.stream()
                .filter(v -> Objects.equals(SysConstants.MENU_PARENT_TOP_ID, v.getPid()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return vos;
    }

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    List<SysMenuDO> findMenuByUserId(Long userId);
    /**
     * 根据用户id查询所拥有的按钮（权限）
     * @param userId
     * @return
     */
    List<SysMenuDO> findBtnByUserId(Long userId);

}
