package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.common.domain.BaseQueryRequest;

/**
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysDictQuery extends BaseQueryRequest {

    private String name;
}
