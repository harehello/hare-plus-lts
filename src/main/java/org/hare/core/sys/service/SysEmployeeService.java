package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.core.sys.dto.SysEmployeeDTO;
import org.hare.core.sys.dto.SysEmployeeQuery;
import org.hare.core.sys.model.SysEmployeeDO;
import org.hare.framework.jpa.BaseService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author wangcheng
 */
public interface SysEmployeeService extends BaseService<SysEmployeeDO, Long> {

    /**
     * 构建查询条件
     * @param query
     * @return
     */
    default Specification<SysEmployeeDO> specification(SysEmployeeQuery query) {

        return new HareSpecification<SysEmployeeDO>()
                .eq(Objects.nonNull(query.getDeptId()), "dept", query.getDeptId())
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .eq(StringUtils.hasText(query.getStatus()), "status", query.getStatus())
                .asc("id");
    }
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
