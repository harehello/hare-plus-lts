package org.hare.core.sys.service;

import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.vo.SysDictVO;
import org.hare.framework.jpa.BaseService;

import java.util.List;

/**
 * SysDict
 *
 * @author wangcheng
 */
public interface SysDictService extends BaseService<SysDictDO, Long> {

    List<SysDictVO> findVo();

}
