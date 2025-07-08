package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.core.sys.constant.SysConstants;
import org.hare.core.sys.dto.SysDeptQuery;
import org.hare.core.sys.model.SysDeptDO;
import org.hare.core.sys.repository.SysDeptRepository;
import org.hare.core.sys.service.SysDeptService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysDeptServiceImpl implements SysDeptService {

    private final SysDeptRepository repository;

    @Override
    public SysDeptDO create(SysDeptDO entity) {

        // 更新父级码和层级
        final SysDeptDO parent = findById(entity.getPid());
        entity.setLevel(getLevel(parent));
        entity.setFastId(getFastId(parent));
        repository.save(entity);
        // 更新父级码和层级
        entity.setFastId(entity.getFastId() + "-"+ entity.getId());
        return repository.save(entity);
    }

    @Override
    public SysDeptDO update(SysDeptDO entity) {
        return create(entity);
    }

    @Override
    public SysDeptDO findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Page<SysDeptDO> findPage(SysDeptQuery query) {
        Specification<SysDeptDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysDeptDO> findList(SysDeptQuery query) {
        Specification<SysDeptDO> specification = specification(query);
        return repository.findAll(specification);
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
