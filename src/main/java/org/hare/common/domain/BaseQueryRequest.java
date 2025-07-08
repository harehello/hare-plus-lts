
package org.hare.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

/**
 * QueryDTO
 *
 * @author wang cheng
 */
@Getter
@Setter
public abstract class BaseQueryRequest implements QueryRequest {

    protected Long deptId;
    /**
     * 当前页码
     */
    protected Integer page;
    /**
     * 分页尺寸
     */
    protected Integer limit;

    /**
     * 排序字段
     */
    protected String field;
    /**
     * 排序方式
     */
    protected String order;

    public Pageable getPageable() {

        if (Objects.isNull(page)) {
            page = 1;
        }
        if (Objects.isNull(limit)) {
            limit = 10;
        }

        return PageRequest.of(page -1, limit);
    }
}
