package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.core.sys.model.SysJobDO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 岗位
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysJobDTO {

    private Long id;
    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    private String name;
    /**
     * 岗位编码
     */
    @NotBlank(message = "岗位编码不能为空")
    private String code;
    /**
     * 排序
     */
    @NotNull(message = "岗位排序不能为空")
    private Integer seq;
    /**
     * 状态
     * 正常、停用
     * active inactive
     */
    @NotBlank(message = "岗位状态不能为空")
    private String status;

    /**
     * 作业方式
     */
    private String mode;
    /**
     * 作业时长
     * 小时/天
     */
    private Integer duration;

    public static SysJobDO convert(SysJobDTO dto) {
        SysJobDO sysPostDO = new SysJobDO();
        sysPostDO.setName(dto.getName());
        sysPostDO.setCode(dto.getCode());
        sysPostDO.setSeq(dto.getSeq());
        sysPostDO.setStatus(dto.getStatus());
        sysPostDO.setMode(dto.getMode());
        sysPostDO.setDuration(dto.getDuration());
        sysPostDO.setId(dto.getId());
        return sysPostDO;

    }
}

