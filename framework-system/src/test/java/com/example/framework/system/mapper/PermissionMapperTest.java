package com.example.framework.system.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@MybatisPlusTest
class PermissionMapperTest extends TenantBaseCase {

	@Autowired
	PermissionMapper permissionMapper;

	@Test
	void testSelectList() {
		assertThat(permissionMapper.selectList(null)).isEmpty();
	}

}
