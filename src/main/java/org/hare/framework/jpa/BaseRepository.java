package org.hare.framework.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义的基础Repository
 *
 * @author wang cheng
 */
@NoRepositoryBean
public interface BaseRepository<T , ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


}
