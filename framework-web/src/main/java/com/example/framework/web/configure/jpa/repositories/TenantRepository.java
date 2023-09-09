package com.example.framework.web.configure.jpa.repositories;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author MaGuangZu
 * @since 2023-09-07
 */
@NoRepositoryBean
public interface TenantRepository<T> extends BaseRepository<T> {

}
