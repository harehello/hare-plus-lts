package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hare.common.domain.QueryRequest;

/**
 * 岗位查询
 * @author wang cheng
 */
@Getter
@Setter
@ToString
public class SysJobQuery extends QueryRequest {

    private static final long serialVersionUID = -6739582658982637919L;
    private String name;
    private String status;
}
