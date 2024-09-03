package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.hare.common.domain.QueryRequest;

/**
 * 用户查询
 * @author wang cheng
 */
@Getter
@Setter
public class SysRoleQuery extends QueryRequest {

    private static final long serialVersionUID = 5435197692295220957L;

    private String name;
}
