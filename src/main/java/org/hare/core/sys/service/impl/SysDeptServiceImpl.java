package org.hare.core.sys.service.impl;

import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.service.SysDeptService;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wang cheng
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDO, Long> implements SysDeptService {

    @Override
    public SysDeptDO save(SysDeptDO entity) {

//        repository.save(entity);
        // 父级码
//        String parentIds = getParentIds(entity.getPid(), entity.getId());
//        int frequency = StringUtils.frequency(parentIds, SysConstants.DEPT_PARENT_SEPARATOR);
//        // 父级码包含自身减掉一级
//        entity.setLevel(frequency > 0 ? (frequency - 1) : frequency);
//        entity.setParentIds(parentIds);
        // 更新父级码和层级
        final SysDeptDO parent = findById(entity.getPid());
        entity.setLevel(getLevel(parent));
        entity.setFastId(getFastId(parent));
        repository.save(entity);
        // 更新父级码和层级
        entity.setFastId(entity.getFastId() + "-"+ entity.getId());
        return repository.save(entity);
    }

    /**
     * 获取上级的id串
     * @param parentId
     * @return
     */
    private String getParentIds(Long parentId, Long id) {
        if (Objects.equals(SysConstants.DEPT_PARENT_TOP, parentId)) {
            return SysConstants.DEPT_PARENT_TOP + SysConstants.DEPT_PARENT_SEPARATOR + id + SysConstants.DEPT_PARENT_SEPARATOR;
        }
        SysDeptDO deptDO = findById(parentId);
//        return deptDO.getParentIds() + id + SysConstants.DEPT_PARENT_SEPARATOR;
        return id + SysConstants.DEPT_PARENT_SEPARATOR;

    }

    /**
     * 获取层级
     * @param parent
     * @return
     */
    private int getLevel(SysDeptDO parent) {
        return 1 + parent.getLevel();
    }

    /**
     * 获取层级
     * @param parent
     * @return
     */
    private String getFastId(SysDeptDO parent) {
        return parent.getFastId();
    }

}
