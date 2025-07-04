package org.hare.core.sys.service;

import org.hare.common.domain.OptionResponse;

import java.util.List;

/**
 * 用户主体
 * @author wang cheng
 */
public interface SysUserSubjectService {
    /**
     * 用户主体类型列表
     */
    List<OptionResponse> getType();
    /**
     * 用户主体列表
     */
    List<OptionResponse> getOption(String type);
}
