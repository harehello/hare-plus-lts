package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.SysDictItemQuery;
import org.hare.core.sys.model.SysDictItemDO;
import org.hare.core.sys.repository.SysDictItemRepository;
import org.hare.core.sys.service.SysDictItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysDictItemServiceImpl implements SysDictItemService {

    private final SysDictItemRepository repository;

    @Override
    public SysDictItemDO create(SysDictItemDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysDictItemDO update(SysDictItemDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysDictItemDO findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long aLong) {

        repository.deleteById(aLong);
    }

    @Override
    public void deleteAllById(List<Long> ids) {

        repository.deleteAllById(ids);
    }

    @Override
    public Specification<SysDictItemDO> specification(SysDictItemQuery query) {
        return new HareSpecification<SysDictItemDO>()
                .eq("dict", query.getDictId())
                .asc("seq");
    }

    @Override
    public Page<SysDictItemDO> findPage(SysDictItemQuery query) {
        final Specification<SysDictItemDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysDictItemDO> findList(SysDictItemQuery query) {
        final Specification<SysDictItemDO> specification = specification(query);
        return repository.findAll(specification);
    }
}
