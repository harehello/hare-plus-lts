
package org.hare.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Objects;

/**
 * QueryDTO
 *
 * @author wang cheng
 */
@Getter
@Setter
public class QueryRequest implements Serializable {
    private static final long serialVersionUID = 8937603340808059959L;

    private Long deptId;
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 分页尺寸
     */
    private Integer limit;

    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序方式
     */
    private String order;

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
