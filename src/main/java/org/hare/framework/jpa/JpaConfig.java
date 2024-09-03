package org.hare.framework.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Jpa配置类
 * @author wang cheng
 */
@Configuration
@EnableJpaRepositories(basePackages= "org.hare.core.**.repository", repositoryBaseClass = BaseRepositoryImpl.class)
public class JpaConfig {

}
