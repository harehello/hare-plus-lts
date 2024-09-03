package org.hare.core.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hare.core.sys.model.SysDeptDO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门视图
 * @author wang cheng
 */
@Getter
@Setter
public class SysDeptVO {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String sn;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 介绍
     */
    private String remark;
    /**
     * 父级ID
     */
    private Long pid;
    private String fastId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 子项
     */
    private List<SysDeptVO> children;

    public SysDeptVO() {
    }

    public SysDeptVO(SysDeptDO dept) {
        this.id = dept.getId();
        this.name = dept.getName();
        this.sn = dept.getSn();
        this.level = dept.getLevel();
        this.seq = dept.getSeq();
        this.remark = dept.getRemark();
        this.pid = dept.getPid();
        this.fastId = dept.getFastId();
        this.createTime = dept.getCreateTime();
    }
}
