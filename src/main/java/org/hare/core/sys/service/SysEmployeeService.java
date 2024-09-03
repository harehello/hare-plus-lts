package org.hare.core.sys.service;

import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.model.SysEmployeeDO;
import org.hare.framework.jpa.BaseService;

/**
 * @author wangcheng
 */
public interface SysEmployeeService extends BaseService<SysEmployeeDO, Long> {

    /**
     * 创建员工
     * @param request
     * @return
     */
    SysEmployeeDO create(SysEmployeeDTO request);

    /**
     * 查询员工信息
     * @param id
     * @return
     */
    SysEmployeeDTO findDtoById(Long id);

}
