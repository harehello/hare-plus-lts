package org.hare.core.sys.service;

import com.hare.jpa.HareSpecification;
import org.hare.common.component.CrudService;
import org.hare.core.sys.dto.SysDictQuery;
import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.vo.SysDictVO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * SysDict
 *
 * @author wangcheng
 */
public interface SysDictService extends CrudService<SysDictDO, Long, SysDictQuery> {

    List<SysDictVO> findVo();

    default Specification<SysDictDO> specification(SysDictQuery query) {
        return new HareSpecification<SysDictDO>()
                .eq(StringUtils.hasText(query.getName()), "name", query.getName())
                .asc("seq");
    }
}
