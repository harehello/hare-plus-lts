package org.hare.core.sys.dto;

import lombok.Data;
import org.hare.common.domain.BaseQueryRequest;

/**
 * @author wang cheng
 */
@Data
public class SysDeptQuery extends BaseQueryRequest {

    private String name;
}
