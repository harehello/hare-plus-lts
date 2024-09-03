package org.hare.core.sys.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户/员工表单导入
 * @author wang cheng
 */
@Getter
@Setter
public class SysUserImportDTO {

    public String get;
    /**
     * 人员编号
     */
    @ExcelProperty(index = 0)
    private String code;
    /**
     * 姓名
     */
    @ExcelProperty(index = 1)
    private String fullName;
    /**
     * 性别
     */
    @ExcelProperty(index = 2)
    private String sex;
    /**
     * 年龄
     */
    @ExcelProperty(index = 3)
    private Integer age;
    /**
     * 手机号
     */
    @ExcelProperty(index = 4)
    private String phone;
    /**
     * 部门
     */
    @ExcelProperty(index = 5)
    private String deptName;
    /**
     * 岗位
     */
    @ExcelProperty(index = 6)
    private String post;
    /**
     * 角色
     */
    @ExcelProperty(index = 7)
    private String role;
    /**
     * 入职日期
     */
    @ExcelProperty(index = 8)
    @DateTimeFormat("yyyy-MM-dd")
    private Date entryDate;

}
