package org.hare.core.sys.dto;

import lombok.Getter;
import lombok.Setter;
import org.hare.common.domain.BaseQueryRequest;

/**
 * 用户查询
 * @author wang cheng
 */
@Getter
@Setter
public class SysUserQuery extends BaseQueryRequest {

    private String nickname;
    private String subject;
    private Long subjectId;
    private String status;
    private String role;
}
