package org.hare.core.sys.service.impl;

import lombok.RequiredArgsConstructor;
import org.hare.common.domain.OptionResponse;
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
    public List<OptionResponse> getType() {
        return strategies.stream()
                .map(strategy ->
                        new OptionResponse(strategy.name(), strategy.code())
                ).collect(Collectors.toList());
    }

    @Override
    public List<OptionResponse> getOption(String type) {
        final Optional<SysUserSubjectStrategy> first = strategies.stream()
                .filter(s -> s.code().equals(type))
                .findFirst();

        return first.map(strategy -> strategy.option()
                .stream()
                .map(option -> new OptionResponse(option.getLabel(), option.getValue()))
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }
}
