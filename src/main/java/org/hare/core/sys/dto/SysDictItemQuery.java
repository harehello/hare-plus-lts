package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.hare.common.domain.BaseQueryRequest;

/**
 * @author wang cheng
 */
@Getter
@Setter
public class SysDictItemQuery extends BaseQueryRequest {

    private Long dictId;
}
