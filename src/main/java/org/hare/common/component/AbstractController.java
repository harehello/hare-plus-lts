package org.hare.common.component;
import org.hare.core.sys.service.SysDeptService;
import org.hare.core.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AbstractController
 *
 * @author wang cheng
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SysUserService userService;
    @Autowired
    protected SysDeptService deptService;
}
