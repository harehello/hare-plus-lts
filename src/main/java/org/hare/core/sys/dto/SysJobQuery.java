package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.common.domain.BaseQueryRequest;

/**
 * 岗位查询
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysJobQuery extends BaseQueryRequest {

    private String name;
    private String status;
}
