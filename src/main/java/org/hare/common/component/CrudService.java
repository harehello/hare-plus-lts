package org.hare.common.component;

import org.hare.common.domain.BaseQueryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 * @author wang cheng
 */
public interface CrudService<T, ID extends Serializable, Q extends BaseQueryRequest> {

    /**
     * 创建
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 删除
     * @param id
     */
    void deleteById(ID id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteAllById(List<ID> ids);

    /**
     * 查询分页列表
     * @param query
     * @return
     */
    Page<T> findPage(Q query);
    /**
     * 查询列表
     * @param query
     * @return
     */
    List<T> findList(Q query);

    Specification<T> specification(Q query);
}
