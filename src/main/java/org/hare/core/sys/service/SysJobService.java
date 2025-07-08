package org.hare.core.sys.service;

import org.hare.common.component.CrudService;
import org.hare.core.sys.dto.SysJobDTO;
import org.hare.core.sys.dto.SysJobQuery;
import org.hare.core.sys.model.SysJobDO;

/**
 * @author wangcheng
 */
public interface SysJobService extends CrudService<SysJobDO, Long, SysJobQuery> {

    /**
     * 创建
     *
     * @param request
     * @return
     */
    SysJobDO create(SysJobDTO request);
    /**
     * 修改
     *
     * @param request
     * @return
     */
    SysJobDO update(SysJobDTO request);
}
