package org.hare.framework.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * 顶级service
 * @author wang cheng
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 获取实体class
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 保存给定的实体
     * @see org.springframework.data.repository.CrudRepository#save
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S save(S entity);

    /**
     * 保存所有给定的实体
     * @see org.springframework.data.repository.CrudRepository#saveAll
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * 删除具有给定 ID 的实体
     * @see org.springframework.data.repository.CrudRepository#deleteById
     * @param id
     */
    void deleteById(ID id);

    /**
     * 删除给定实体
     * @see org.springframework.data.repository.CrudRepository#delete
     * @param entity
     */
    void delete(T entity);

    /**
     * 删除具有给定 ID 的 T 类型的所有实例
     * @see org.springframework.data.repository.CrudRepository#deleteAllById
     * @param ids
     */
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * 删除给定的实体
     * @see org.springframework.data.repository.CrudRepository#deleteAll
     * @param entities
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * 删除存储库管理的所有实体
     * @see org.springframework.data.repository.CrudRepository#deleteAll
     */
    void deleteAll();

    /**
     * 通过 id 检索实体
     * @see org.springframework.data.repository.CrudRepository#findById
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     * 返回与给定规范匹配的单个实体，如果未找到，则返回 Optional.empty()
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findOne
     * @param spec
     * @return
     */
    T findOne(@Nullable Specification<T> spec);

    /**
     * 返回该类型的所有实例
     * @see org.springframework.data.repository.CrudRepository#findAll
     * @return
     */
    List<T> findAll();

    /**
     * 返回按给定选项排序的所有实体
     * @see  org.springframework.data.jpa.repository.JpaRepository#findAll
     * @param sort
     * @return
     */
    List<T> findAll(Sort sort);

    /**
     * 返回具有给定 ID 的 T 类型的所有实例
     * @see  org.springframework.data.repository.CrudRepository#findAllById
     * @param ids
     * @return
     */
    List<T> findAllById(Iterable<ID> ids);

    /**
     * 返回满足 Pageable 对象中提供的分页限制的实体页面
     * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(Pageable)
     * @param pageable
     * @return
     */
    Page<T> findAll(Pageable pageable);

    /**
     * 返回与给定规范匹配的所有实体
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(Specification)
     * @param spec
     * @return
     */
    List<T> findAll(@Nullable Specification<T> spec);

    /**
     * 返回与给定规范匹配的实体页面
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(Specification, Pageable)
     * @param spec
     * @param pageable
     * @return
     */
    Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);

    /**
     * 返回与给定规范和排序匹配的所有实体
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(Specification, Sort)
     * @param spec
     * @param sort
     * @return
     */
    List<T> findAll(@Nullable Specification<T> spec, Sort sort);

    /**
     * 返回可用实体的数量
     * @see org.springframework.data.repository.CrudRepository#count()
     * @return
     */
    long count();

    /**
     * 返回给定规范将返回的实例数
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#count(Specification)
     * @param spec
     * @return
     */
    long count(@Nullable Specification<T> spec);

    /**
     * 检查数据存储是否包含与给定规范匹配的元素
     * @see org.springframework.data.jpa.repository.JpaSpecificationExecutor#exists(Specification)
     * @param spec
     * @return
     */
    boolean exists(Specification<T> spec);

    /**
     * 返回具有给定 id 的实体是否存在
     * @see org.springframework.data.repository.CrudRepository#existsById
     * @param id
     * @return
     */
    boolean existsById(ID id);

    /**
     * 刷新对数据库的所有挂起更改
     * @see org.springframework.data.jpa.repository.JpaRepository#flush()
     */
    void flush();

    /**
     * 保存实体并立即刷新更改
     * @see org.springframework.data.jpa.repository.JpaRepository#saveAndFlush(Object)
     * @param entity
     * @param <S>
     * @return
     */
    <S extends T> S saveAndFlush(S entity);

    /**
     * 保存所有实体并立即刷新更改
     * @see org.springframework.data.jpa.repository.JpaRepository#saveAllAndFlush(Iterable)
     * @param entities
     * @param <S>
     * @return
     */
    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

}
