package org.hare.core.sys.repository;

import org.hare.core.sys.model.SysDeptDO;
import org.hare.framework.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author wang cheng
 */
public interface SysDeptRepository extends BaseRepository<SysDeptDO, Long> {

}
