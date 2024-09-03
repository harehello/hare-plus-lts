package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.core.sys.dto.SysJobQuery;
import org.hare.core.sys.model.SysJobDO;
import org.hare.framework.jpa.BaseService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

/**
 * @author wangcheng
 */
public interface SysJobService extends BaseService<SysJobDO, Long> {

    /**
     * 构建查询条件
     * @param query
     * @return
     */
    default Specification<SysJobDO> specification(SysJobQuery query) {

        return new HareSpecification<SysJobDO> ()
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .like(StringUtils.hasText(query.getStatus()), "status", query.getStatus())
                .asc("seq");
    }
}
