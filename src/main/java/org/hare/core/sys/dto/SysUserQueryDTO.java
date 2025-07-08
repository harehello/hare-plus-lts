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
public class SysUserQueryDTO extends BaseQueryRequest {
    private static final long serialVersionUID = -5290437477286920379L;

    private String nickname;
    private String subject;
    private String status;
    private String role;
}
