package org.hare.framework.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hare.core.sys.dto.LoginUserDTO;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.model.SysEmployeeDO;
import org.hare.core.sys.model.SysUserDO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author wang cheng
 */
public final class BaseEntityListener {
    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     * 保存之前
     * @param entity
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreateTime(now);

//        LoginUserDTO loginUserDTO = getUser();
//        SysUserDO user = loginUserDTO.getUser();
//        entity.setCreateUserId(user.getId());
//        entity.setCreateUser(user.getNickname());
//        entity.setModifiedUserId(user.getId());
//        entity.setModifiedUser(user.getNickname());

//        SysEmployeeDO employee = user.getEmployee();
//        if (Objects.nonNull(employee)) {
//            SysDeptDO dept = employee.getDept();
//            if (Objects.nonNull(dept)) {
////                entity.setCreateDeptId(dept.getId());
//                entity.setCreateDept(dept.getName());
//                entity.setCreateDeptFastId(dept.getFastId());
////                entity.setModifiedDeptId(dept.getId());
//                entity.setModifiedDept(dept.getName());
//            }
//        }

    }

    /**
     * 保存之后
     * @param entity
     */
    @PostPersist
    public void PostPersist(BaseEntity entity) {
//        logger.info("PostPersist ------------------");

    }

    /**
     * 修改前
     * @param entity
     */
    @PreUpdate
    public void PreUpdate(BaseEntity entity) {
//        logger.info("PreUpdate ------------------");
        LocalDateTime now = LocalDateTime.now();
        entity.setModifiedTime(now);

//        LoginUserDTO loginUserDTO = getUser();
//        SysUserDO user = loginUserDTO.getUser();
//
////        entity.setModifiedUserId(user.getId());
//        entity.setModifiedUser(user.getNickname());
//
//        SysEmployeeDO employee = user.getEmployee();
//        if (Objects.nonNull(employee)) {
//            SysDeptDO dept = employee.getDept();
//            if (Objects.nonNull(dept)) {
////                entity.setModifiedDeptId(dept.getId());
//                entity.setModifiedDept(dept.getName());
//            }
//        }
    }

    /**
     * 修改后
     * @param entity
     */
    @PostUpdate
    public void PostUpdate(BaseEntity entity) {
//        logger.info("PostUpdate ------------------");

    }

    /**
     * 删除会后
     * @param entity
     */
    @PostRemove
    public void PostRemove(BaseEntity entity) {
//        logger.info("PostRemove ------------------");
    }

    private LoginUserDTO getUser() {
//        String terminal = SecurityContextUtils.getTerminal();
//        Long userId = SecurityContextUtils.getUserId();
//        List<LoginUserDTO> list = authenticationCacheService.getList(terminal + ":" + userId);
//        if (CollectionUtils.isEmpty(list)) {
//            return null;
//        }
//        return list.get(0);
        return null;
    }
}
