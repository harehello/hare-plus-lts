package org.hare.core.sys.service.impl;

import com.hare.jpa.HareSpecification;
import lombok.RequiredArgsConstructor;
import org.hare.core.sys.dto.SysJobDTO;
import org.hare.core.sys.dto.SysJobQuery;
import org.hare.core.sys.model.SysJobDO;
import org.hare.core.sys.repository.SysJobRepository;
import org.hare.core.sys.service.SysJobService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysJobServiceImpl implements SysJobService {

    private final SysJobRepository repository;

    @Override
    public SysJobDO create(SysJobDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysJobDO update(SysJobDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysJobDO findById(Long aLong) {
        return repository.findById(aLong).orElse( null);
    }

    @Override
    public void deleteById(Long aLong) {

        repository.deleteById(aLong);
    }

    @Override
    public void deleteAllById(List<Long> longs) {

        repository.deleteAllById(longs);
    }

    @Override
    public Page<SysJobDO> findPage(SysJobQuery query) {
        final Specification<SysJobDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysJobDO> findList(SysJobQuery query) {
        return repository.findAll(specification(query));
    }

    @Override
    public Specification<SysJobDO> specification(SysJobQuery query) {
       return new HareSpecification<SysJobDO>()
                .like(StringUtils.hasText(query.getName()), "name", query.getName())
                .eq(StringUtils.hasText(query.getStatus()), "status", query.getStatus())
                .asc("seq");
    }

    @Override
    public SysJobDO create(SysJobDTO request) {
        return create(SysJobDTO.convert(request));
    }

    @Override
    public SysJobDO update(SysJobDTO request) {
        return update(SysJobDTO.convert(request));
    }
}
