package org.hare.framework.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.persistence.*;
import java.time.LocalDateTime;

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

}
