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
public class SysUserQueryDTO extends QueryRequest {
    private static final long serialVersionUID = -5290437477286920379L;

    private String fullName;
    private String dept;
    private Long postId;
}
