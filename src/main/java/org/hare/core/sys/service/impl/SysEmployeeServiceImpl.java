package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.common.constant.StateEmun;
import org.hare.common.domain.OptionResponse;
import org.hare.core.sys.constant.UserSubjectEmun;
import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.dto.SysEmployeeQuery;
import org.hare.core.sys.dto.SysUserDTO;
import org.hare.core.sys.model.*;
import org.hare.core.sys.repository.SysEmployeeRepository;
import org.hare.core.sys.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysEmployeeServiceImpl implements SysEmployeeService, SysUserSubjectStrategy {

    private final SysDeptService deptService;
    private final SysJobService postService;
    private final SysUserService userService;
    private final SysEmployeeRepository repository;

    @Override
    public Specification<SysEmployeeDO> specification(SysEmployeeQuery query) {
        return new HareSpecification<SysEmployeeDO>()
                .eq(Objects.nonNull(query.getDeptId()), "dept", query.getDeptId())
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .eq(StringUtils.hasText(query.getStatus()), "status", query.getStatus())
                .asc("id");
    }

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

        final Long jobId = request.getJobId();
        final SysJobDO jobDO = postService.findById(jobId);
        target.setJob(jobDO);
        // 保存员工信息
        repository.save(target);

        // 自动创建用户
        request.setId(target.getId());
        final SysUserDTO userDTO = SysEmployeeDTO.convertToUser(request);
        userService.create(userDTO);

        return target;
    }

    /**
     * 修改员工，自动修改用户
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysEmployeeDO update(SysEmployeeDTO request) {
        Long id = request.getId();
        Assert.notNull(id, "employee id cannot be null");

        final SysEmployeeDO target = findById(id);
        // 原手机号
        final String rawPhone = target.getPhone();
        // 允许被修改的字段
        target.setName(request.getName());
        target.setCode(request.getCode());
        target.setSex(request.getSex());
        target.setBirthday(request.getBirthday());
        target.setPhone(request.getPhone());
        target.setEntryDate(request.getEntryDate());
        target.setStatus(request.getStatus());
        target.setRemark(request.getRemark());

        final Long deptId = request.getDeptId();
        final SysDeptDO deptDO = deptService.findById(deptId);
        target.setDept(deptDO);

        final Long jobId = request.getJobId();
        final SysJobDO jobDO = postService.findById(jobId);
        target.setJob(jobDO);

        // 保存员工信息
        repository.save(target);

        // 修改用户
        final SysUserDTO userDTO = SysEmployeeDTO.convertToUser(request);

        // 有账号就修改账号 没有账号 就创建一个账号
        final SysUserDO userDO = userService.findByUsername(rawPhone);
        if (Objects.nonNull(userDO)) {
            userDTO.setId(userDO.getId());
            userService.update(userDTO);
        } else {
            userService.create(userDTO);
        }

        return target;
    }

    @Transactional(readOnly = true)
    @Override
    public SysEmployeeDTO findDtoById(Long id) {
        final SysEmployeeDO employeeDO = findById(id);
        final SysEmployeeDTO target = new SysEmployeeDTO(employeeDO);
        final SysUserDO user = userService.findByUsername(employeeDO.getPhone());
        final Set<SysRoleDO> roles = user.getRoles();
        if (Objects.nonNull(roles)) {
            final List<Long> roleIds = roles.stream().map(SysRoleDO::getId).collect(Collectors.toList());
            target.setRoleIds(roleIds);
            final List<String> roleNames = roles.stream().map(SysRoleDO::getName).collect(Collectors.toList());
            target.setRoles(roleNames);
        }
        return target;
    }

    @Override
    public String code() {
        return UserSubjectEmun.employee.name();
    }

    @Override
    public String name() {
        return UserSubjectEmun.employee.getLabel();
    }

    @Override
    public List<OptionResponse> option() {
        return repository.findAll(new HareSpecification<SysEmployeeDO>()
                .eq("status", StateEmun.active.name()))
                .stream()
                .map(e -> new OptionResponse(e.getName(), e.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public SysEmployeeDO create(SysEmployeeDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysEmployeeDO update(SysEmployeeDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysEmployeeDO findById(Long aLong) {
        return repository.findById(aLong).orElse( null);
    }

    @Override
    public void deleteById(Long aLong) {

        repository.deleteById(aLong);
    }

    @Override
    public void deleteAllById(List<Long> longs) {

        repository.deleteAllById(longs);
    }

    @Override
    public Page<SysEmployeeDO> findPage(SysEmployeeQuery query) {
        final Specification<SysEmployeeDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysEmployeeDO> findList(SysEmployeeQuery query) {
        return repository.findAll(specification(query));
    }
}
