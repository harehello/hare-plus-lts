package org.hare.core.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.constant.UserSubjectEmun;
import org.hare.core.sys.model.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 员工请求体
 * @author wang cheng
 */
@Getter
@Setter
public class SysEmployeeDTO {

    /**
     * 员工ID
     */
    private Long id;
    /**
     * 部门
     */
    @NotNull(message = "部门不能为空")
    private Long deptId;
    private String deptName;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;
    /**
     * 人员编号
     */
    @NotBlank(message = "人员编号不能为空")
    private String code;
    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空")
    private String status;
    /**
     * 岗位
     */
    @NotNull(message = "岗位不能为空")
    private Long jobId;

    private String jobName;
    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "入职日期不能为空")
    private Date entryDate;


    /**
     * 角色集合
     */
    @NotEmpty(message = "角色不能为空")
    private List<Long> roleIds;
    private List<String> roles;

    /**
     * 备注
     */
    private String remark;

    public SysEmployeeDTO() {
    }

    public SysEmployeeDTO(SysEmployeeDO employee) {
        this.id = employee.getId();
        final SysDeptDO deptDO = employee.getDept();
        if (Objects.nonNull(deptDO)) {
            this.deptId = deptDO.getId();
            this.deptName = deptDO.getName();
        }
        this.name = employee.getName();
        this.code = employee.getCode();
        this.sex = employee.getSex();
        this.phone = employee.getPhone();
        this.birthday = employee.getBirthday();
        this.status = employee.getStatus();
        final SysJobDO job = employee.getJob();
        if (Objects.nonNull(job)) {
            this.jobId = job.getId();
            this.jobName = job.getName();
        }
        this.entryDate = employee.getEntryDate();

        this.remark = employee.getRemark();
    }

    public static SysEmployeeDO convert(SysEmployeeDTO dto) {
        SysEmployeeDO sysEmployeeDO = new SysEmployeeDO();
        sysEmployeeDO.setName(dto.getName());
        sysEmployeeDO.setCode(dto.getCode());
        sysEmployeeDO.setSex(dto.getSex());
        sysEmployeeDO.setBirthday(dto.getBirthday());
        sysEmployeeDO.setPhone(dto.getPhone());
        sysEmployeeDO.setEntryDate(dto.getEntryDate());
        sysEmployeeDO.setStatus(dto.getStatus());
        sysEmployeeDO.setRemark(dto.getRemark());
        sysEmployeeDO.setId(dto.getId());
        return sysEmployeeDO;

    }

    public static SysUserDTO convertToUser(SysEmployeeDTO dto) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUsername(dto.getPhone());
        sysUserDTO.setNickname(dto.getName());
        sysUserDTO.setSubject(UserSubjectEmun.employee.name());
        sysUserDTO.setSubjectId(dto.getId());
        sysUserDTO.setStatus(dto.getStatus());
        sysUserDTO.setRoleIds(dto.getRoleIds());
        return sysUserDTO;
    }
}
