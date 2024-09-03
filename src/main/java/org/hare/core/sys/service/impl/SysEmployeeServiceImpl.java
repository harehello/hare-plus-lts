package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.model.*;
import org.hare.core.sys.service.SysDeptService;
import org.hare.core.sys.service.SysEmployeeService;
import org.hare.core.sys.service.SysJobService;
import org.hare.core.sys.service.SysUserService;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysEmployeeServiceImpl extends BaseServiceImpl<SysEmployeeDO, Long> implements SysEmployeeService {

    private final SysDeptService deptService;
    private final SysJobService postService;
    private final SysUserService userService;

    /**
     * 创建员工，自动创建用户
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysEmployeeDO create(SysEmployeeDTO request) {

        final SysEmployeeDO target = SysEmployeeDTO.convert(request);

        final Long deptId = request.getDeptId();
        final SysDeptDO deptDO = deptService.findById(deptId);
        target.setDept(deptDO);

        final Long postId = request.getJobId();
        final SysJobDO jobDO = postService.findById(postId);
        target.setJob(jobDO);
        // 保存员工信息
        super.save(target);

        // 自动创建用户
        final SysUserDTO userDTO = SysEmployeeDTO.convertToUser(request);
        userService.create(userDTO);

        return target;
    }

    @Transactional(readOnly = true)
    @Override
    public SysEmployeeDTO findDtoById(Long id) {
        final SysEmployeeDO employeeDO = findById(id);
        final SysEmployeeDTO target = new SysEmployeeDTO(employeeDO);
        final SysUserDO user = userService.findById(employeeDO.getUserId());
        final Set<SysRoleDO> roles = user.getRoles();
        if (Objects.nonNull(roles)) {
            final List<Long> roleIds = roles.stream().map(SysRoleDO::getId).collect(Collectors.toList());
            target.setRoleIds(roleIds);
            final List<String> roleNames = roles.stream().map(SysRoleDO::getName).collect(Collectors.toList());
            target.setRoles(roleNames);
        }
        return target;
    }
}
