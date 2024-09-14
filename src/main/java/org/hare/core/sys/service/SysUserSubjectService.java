package org.hare.core.sys.service;

import org.hare.common.domain.LabelValueVO;

import java.util.List;

/**
 * 用户主体
 * @author wang cheng
 */
public interface SysUserSubjectService {
    /**
     * 用户主体类型列表
     */
    List<LabelValueVO> getType();
    /**
     * 用户主体列表
     */
    List<LabelValueVO> getOption(String type);
}
