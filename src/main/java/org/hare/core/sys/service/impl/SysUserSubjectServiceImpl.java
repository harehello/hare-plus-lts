package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.common.domain.LabelValueVO;
import org.hare.core.sys.service.SysUserSubjectService;
import org.hare.core.sys.service.SysUserSubjectStrategy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wang cheng
 */
@RequiredArgsConstructor
@Component
public class SysUserSubjectServiceImpl implements SysUserSubjectService {

    private final List<SysUserSubjectStrategy> strategies;

    @Override
    public List<LabelValueVO> getType() {
        return strategies.stream()
                .map(strategy ->
                        new LabelValueVO(strategy.name(), strategy.code())
                ).collect(Collectors.toList());
    }

    @Override
    public List<LabelValueVO> getOption(String type) {
        final Optional<SysUserSubjectStrategy> first = strategies.stream()
                .filter(s -> s.code().equals(type))
                .findFirst();

        System.out.println(first.get().code());
        System.out.println(first.get().option().size());

        return first.map(strategy -> strategy.option()
                .stream()
                .map(option -> new LabelValueVO(option.getLabel(), option.getValue()))
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }
}
