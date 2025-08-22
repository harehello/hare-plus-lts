package org.hare.core.sys.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hare.common.domain.BaseQueryRequest;

import java.util.Date;

/**
 * 员工请求体
 * @author wang cheng
 */
@Getter
@Setter
public class SysEmployeeQuery extends BaseQueryRequest {

    /**
     * 姓名
     */
    private String name;
    /**
     * 人员编号
     */
    private String code;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态
     */
    private String status;
    /**
     * 岗位
     */
    private Long jobId;

    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
}
