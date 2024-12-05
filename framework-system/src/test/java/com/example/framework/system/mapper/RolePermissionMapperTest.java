package com.example.framework.system.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.framework.system.TenantBaseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-08-31
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisPlusTest
class RolePermissionMapperTest extends TenantBaseCase {

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Test
	void testSelectList() {
		assertThat(rolePermissionMapper.selectList(null)).isEmpty();
	}

}
