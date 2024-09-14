package org.hare.core.sys.service;

import org.hare.common.domain.LabelValueVO;

import java.util.List;

/**
 * 用户主体策略
 * @author wang cheng
 */
public interface SysUserSubjectStrategy {

    /**
     * 主体定义的编码
     */
    String code();

    /**
     * 主体定义的名字
     */
    String name();

    /**
     * 主体选项
     */
    List<LabelValueVO> option();
}
