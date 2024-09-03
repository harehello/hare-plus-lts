package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.model.*;
import org.hare.core.sys.service.SysEmployeeService;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.exception.BaseException;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDO, Long> implements SysUserService {

    private final SysRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Override
    public SysUserDO findByUsername(String username) {
        return findOne(new HareSpecification<SysUserDO>().eq("username", username));
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public SysUserDO findById(Long id) {
        return super.findById(id);
    }

    /**
     * 保存员工信息并生成账号
     * @param userDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUserDO create(SysUserDTO userDTO) {
        // 设置用户信息
        SysUserDO target = SysUserDTO.convert(userDTO);
        target.setPassword(encodePassword(getDefaultPassword()));
        target.setStatus(Constants.NORMAL);

        // 赋值角色
        setRoles(target, userDTO.getRoleIds());
        // 保存用户（同时保存角色信息）并返回
        try {
            super.save(target);
        } catch (DataIntegrityViolationException e) {
            throw new BaseException("账号已存在");
        }
        return target;
    }

    /**
     * 保存员工信息并生成账号
     * @param bodyDTOs
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(List<SysUserDTO> bodyDTOs) {

        for (SysUserDTO bodyDTO : bodyDTOs) {
            create(bodyDTO);
        }

    }

    /**
     * 修改员工信息、账号信息
     * @param userDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysUserDO update(SysUserDTO userDTO) {
        Long userId = userDTO.getId();
        Assert.notNull(userId, "user id cannot be null");
        userCannotUpdate(userId);

        SysUserDO userDO = findById(userId);

        userDO.setUsername(userDTO.getUsername());
        userDO.setNickname(userDTO.getUsername());
        userDO.setStatus(userDTO.getStatus());
        // 赋值角色
        setRoles(userDO, userDTO.getRoleIds());
        try {
            super.save(userDO);
        } catch (DataIntegrityViolationException e) {
            throw new BaseException("账号已存在");
        }
        return userDO;
    }

    /**
     * 更新密码
     * @param id
     * @param password
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(Long id, String password, String rawPassword) {
        Assert.notNull(id, "id cannot be null");
        Assert.hasText(password, "password cannot be null");
//        userCannotUpdate(id);
        SysUserDO userDO = findById(id);
        boolean matches = passwordEncoder.matches(rawPassword, userDO.getPassword());
        if (!matches) {
            throw new BaseException(SysConstants.USER_RAW_PASSWORD_ERROR_MSG);
        }
        userDO.setPassword(encodePassword(password));
        super.save(userDO);
    }

    /**
     * 重置密码
     * @param id
     */
    @Override
    public void resetPassword(Long id) {
        Assert.notNull(id, "id cannot be null");
        userCannotUpdate(id);
        SysUserDO userDO = findById(id);
        userDO.setPassword(encodePassword(getDefaultPassword()));
        super.save(userDO);
    }

    /**
     * 获取默认密码
     * @return
     */
    private String getDefaultPassword() {
        return SysConstants.USER_DEFAULT_PASSWORD;
    }

    /**
     * 获取默认密码
     * @return
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * 赋值员工信息
     * @param userDO
     * @param roleIds
     */
    private void setRoles(SysUserDO userDO, List<Long> roleIds) {
        if (!roleIds.isEmpty()) {
            // 获取角色信息
            List<SysRoleDO> roleDOS = roleService.findAllById(roleIds);
            // 角色名称
            Set<String> role = new LinkedHashSet<>();
            Set<Long> roleIds2 = new LinkedHashSet<>();
            roleDOS.forEach(r -> {
                role.add(r.getName());
                roleIds2.add(r.getId());
            });
            userDO.setRoles(new LinkedHashSet<>(roleDOS));
//            userDO.setRole(StringUtils.join(role, Constants.CSV));
//            userDO.setRoleIds(StringUtils.join(roleIds2, Constants.CSV));
        } else {
            userDO.setRoles(new HashSet<>());
        }
    }

    /**
     * 删除具有给定 ID 的实体
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        userCannotUpdate(id);
        repository.deleteById(id);
    }

    /**
     * 重写父类，系统账号不可删除
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        ids.forEach(id -> userCannotUpdate(id));
        repository.deleteAllById(ids);
    }

    /**
     * 用不信息不可被修改
     * @param id
     */
    private void userCannotUpdate(Long id) {
        if (Objects.equals(SysConstants.USER_SYSTEM_ID, id)) {
            throw new BaseException(SysConstants.USER_CANNOT_UPDATE_MSG);
        }
    }
}
