package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.model.SysMenuDO;
import org.hare.core.sys.service.SysMenuService;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单服务实现类
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDO, Long> implements SysMenuService {

    /**
     * 创建
     * @param form
     * @return
     */
    @Override
    public SysMenuDO create(SysMenuDO form) {
        return super.save(form);
    }

    /**
     * 查询用户菜单，系统管理员账号查询全部
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuDO> findMenuByUserId(Long userId) {
        return findAll(new HareSpecification<SysMenuDO>()
                .eq(!Objects.equals(SysConstants.USER_SYSTEM_ID, userId),"roles.users", userId)
                .eq("type", SysConstants.MENU_TYPE_MENU)
                .distinct()
                .asc("seq"));
    }

    /**
     * 查询用户权限，系统管理员账号查询全部
     * @param userId
     * @return
     */
    @Override
    public List<SysMenuDO> findBtnByUserId(Long userId) {
        List<SysMenuDO> list = findAll(new HareSpecification<SysMenuDO>()
                .eq(!Objects.equals(SysConstants.USER_SYSTEM_ID, userId),"roles.users", userId)
                .eq("type", SysConstants.MENU_TYPE_BUTTON)
                .distinct()
                .asc("seq"));

        return list.stream()
                .filter(m -> StringUtils.hasText(m.getPermissions()))
                .collect(Collectors.toList());
    }


}
