package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.common.domain.OptionResponse;
import org.hare.core.sys.dto.SysDictQuery;
import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.repository.SysDictRepository;
import org.hare.core.sys.service.SysDictService;
import org.hare.core.sys.vo.SysDictVO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典服务实现
 * @author wang cheng
 */
@RequiredArgsConstructor
@Service
public class SysDictServiceImpl implements SysDictService {

    private final SysDictRepository repository;

    @Override
    public List<SysDictVO> findVo() {
        List<SysDictDO> all = repository.findAll();
        List<SysDictVO> vos = all.stream().map(d -> {
            SysDictVO dictVO = new SysDictVO();
            dictVO.setCode(d.getCode());
            dictVO.setName(d.getName());
            List<OptionResponse> labelValueVOS = d.getItems().stream()
                    .map(item -> new OptionResponse(item.getValue(), item.getValue())).collect(Collectors.toList());
            dictVO.setItems(labelValueVOS);
            return dictVO;
        }).collect(Collectors.toList());
        return vos;
    }

    @Override
    public SysDictDO create(SysDictDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysDictDO update(SysDictDO entity) {
        return repository.save(entity);
    }

    @Override
    public SysDictDO findById(Long id) {
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
    public Page<SysDictDO> findPage(SysDictQuery query) {
        Specification<SysDictDO> specification = specification(query);
        return repository.findAll(specification, query.getPageable());
    }

    @Override
    public List<SysDictDO> findList(SysDictQuery query) {
        return repository.findAll(specification(query));
    }
}
