package org.hare.core.sys.service.impl;

import org.hare.common.domain.OptionResponse;
import org.hare.core.sys.model.SysDictDO;
import org.hare.core.sys.service.SysDictService;
import org.hare.core.sys.vo.SysDictVO;
import org.hare.framework.jpa.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictDO, Long> implements SysDictService {

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
}
