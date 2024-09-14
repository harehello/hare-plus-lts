package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.Constants;
import org.hare.common.constant.StutesEmun;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.model.SysRoleDO;
import org.hare.core.sys.model.SysUserDO;
import org.hare.core.sys.service.SysRoleService;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.exception.BaseException;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDO, Long> implements SysUserService {

    private final SysRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 查询用户列表转换为DTO
     * @param specification 条件
     * @return 分页数据
     */
    @Override
    public Page<SysUserDTO> findPage(Specification<SysUserDO> specification, Pageable pageable) {

        final Page<SysUserDO> all = findAll(specification, pageable);

        return all.map(SysUserDTO::convert);
    }

    @Override
    public List<SysUserDTO> findList(Specification<SysUserDO> specification) {
        final List<SysUserDO> all = findAll(specification);

        return all.stream().map(SysUserDTO::convert).collect(Collectors.toList());
    }

    /**
     * 查询用户转换为DTO
     * @param id 用户数据
     * @return SysUserDTO数据对象
     */
    @Transactional(readOnly = true)
    @Override
    public SysUserDTO getById(Long id) {
        final SysUserDO target = findById(id);
        final SysUserDTO dto = SysUserDTO.convert(target);
        final Set<SysRoleDO> roles = target.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            dto.setRoleIds(roles.stream().map(SysRoleDO::getId).collect(Collectors.toList()));
        }
        return dto;
    }

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
     *
     * @param username 要检查的用户名
     * @return 如果用户名唯一返回true，否则返回false
     */
    @Override
    public boolean isUsernameUnique(String username) {
        return isUsernameUnique(username, null);
    }

    @Override
    public boolean isUsernameUnique(String username, Long userId) {

        final long count = count(new HareSpecification<SysUserDO>()
                .eq("username", username)
                .ne(Objects.nonNull(userId), "id", userId));

        return count > 0;
    }

    @Override
    public void assertUsernameUnique(String username) {
        if (isUsernameUnique(username)) {
            throw new BaseException(SysConstants.USER_UNIQUE_ERROR_MSG);
        }
    }

    @Override
    public void assertUsernameUnique(String username, Long userId) {
        if (isUsernameUnique(username, userId)) {
            throw new BaseException(SysConstants.USER_UNIQUE_ERROR_MSG);
        }
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

        // 断言用户唯一
        assertUsernameUnique(target.getUsername());

        target.setPassword(encodePassword(getDefaultPassword()));
        target.setStatus(StutesEmun.active.name());

        // 赋值角色
        setRoles(target, userDTO.getRoleIds());
        // 保存用户（同时保存角色信息）并返回
        return super.save(target);
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

        assertUserAlter(userId);
        assertUsernameUnique(userDTO.getUsername(), userId);

        SysUserDO userDO = findById(userId);

        userDO.setUsername(userDTO.getUsername());
        userDO.setNickname(userDTO.getNickname());
        userDO.setStatus(userDTO.getStatus());
        // 赋值角色
        setRoles(userDO, userDTO.getRoleIds());

        return super.save(userDO);
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
        assertUserAlter(id);
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
            List<SysRoleDO> roles = roleService.findAllById(roleIds);
            // 角色名称
            String role = roles.stream().map(SysRoleDO::getName).collect(Collectors.joining(Constants.CSV));
            userDO.setRoles(new LinkedHashSet<>(roles));
            userDO.setRole(role);
        } else {
            userDO.setRoles(new HashSet<>());
            userDO.setRole(null);
        }
    }

    /**
     * 删除具有给定 ID 的实体
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        assertUserAlter(id);
        repository.deleteById(id);
    }

    /**
     * 重写父类，系统账号不可删除
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        ids.forEach(this::assertUserAlter);
        repository.deleteAllById(ids);
    }

    /**
     * 断言用户更改
     * @param id
     */
    private void assertUserAlter(Long id) {
        if (Objects.equals(SysConstants.USER_SYSTEM_ID, id)) {
            throw new BaseException(SysConstants.USER_CANNOT_UPDATE_MSG);
        }
    }
}
