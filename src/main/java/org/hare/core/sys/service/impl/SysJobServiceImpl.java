package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.core.sys.model.SysJobDO;
import org.hare.core.sys.service.SysJobService;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysJobServiceImpl extends BaseServiceImpl<SysJobDO, Long> implements SysJobService {


}
