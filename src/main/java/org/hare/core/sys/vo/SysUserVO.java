package org.hare.core.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.model.SysUserDO;

import java.util.Date;
import java.util.List;

/**
 * @author wang cheng
 */
@Getter
@Setter
public class SysUserVO {

    private Long id;
    private Long employeeId;
    private String code;
    private String fullName;
    private String sex;
    private Integer age;
    private String phone;
    private Long deptId;
    private String deptName;
    private Long postId;
    private String postName;
    private String postType;
    private String postHazard;
    private Integer postTouchDuration;
    private String postOperatingType;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
    private List<Long> roleIds;
    private String role;
    private String healthFile;

    public SysUserVO(SysUserDO userDO) {
        this.id = userDO.getId();
        this.role = userDO.getRole();

//        SysEmployeeDO employee = userDO.getEmployee();
//        if (Objects.nonNull(employee)) {
//            this.employeeId = employee.getId();
//            this.code = employee.getCode();
//            this.fullName = employee.getFullName();
//            this.sex = employee.getSex();
//            this.age = employee.getAge();
//            this.phone = employee.getPhone();
//            this.entryDate = employee.getEntryDate();
//            this.healthFile = employee.getHealthFile();
//
//            SysDeptDO dept = employee.getDept();
//            if (Objects.nonNull(dept)) {
//                this.deptId = dept.getId();
//                this.deptName = dept.getName();
//            }
//
//            SysPostDO post = employee.getPost();
//            if (Objects.nonNull(post)) {
//                this.postId = post.getId();
//                this.postName = post.getName();
//                this.postType = post.getType();
//                this.postOperatingType = post.getOperatingType();
//            }
//        }
    }
}
