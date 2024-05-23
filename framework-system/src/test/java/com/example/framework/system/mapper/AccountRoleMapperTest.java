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
class AccountRoleMapperTest extends TenantBaseCase {

	@Autowired
	AccountRoleMapper accountRoleMapper;

	@Test
	void testSelectList() {
		assertThat(accountRoleMapper.selectList(null)).isEmpty();
	}

}
