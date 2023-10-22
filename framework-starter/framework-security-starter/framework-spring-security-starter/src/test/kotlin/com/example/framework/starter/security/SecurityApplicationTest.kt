package com.example.framework.starter.security

import com.example.framework.system.repository.AccountRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService

@SpringBootTest
class SecurityApplicationTest {

	@Autowired
	lateinit var userDetailsService: UserDetailsService

	@Autowired
	lateinit var accountRepository: AccountRepository

	@BeforeEach
	fun `create user`() {
//		accountRepository.save()
	}

	@Test
	fun `get `() {

	}
}
