package org.hare.core.sys.service;

import org.hare.common.component.CrudService;
import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.dto.SysEmployeeQuery;
import org.hare.core.sys.model.SysEmployeeDO;

/**
 * @author wangcheng
 */
public interface SysEmployeeService extends CrudService<SysEmployeeDO, Long, SysEmployeeQuery> {

    /**
     * 创建员工
     * @param request
     * @return
     */
    SysEmployeeDO create(SysEmployeeDTO request);

    /**
     * 修改员工
     * @param request
     * @return
     */
    SysEmployeeDO update(SysEmployeeDTO request);

    /**
     * 查询员工信息
     * @param id
     * @return
     */
    SysEmployeeDTO findDtoById(Long id);

}
