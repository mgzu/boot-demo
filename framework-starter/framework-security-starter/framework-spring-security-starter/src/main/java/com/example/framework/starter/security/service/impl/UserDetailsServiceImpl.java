package com.example.framework.starter.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.framework.starter.security.converter.AccountConverter;
import com.example.framework.system.entity.Account;
import com.example.framework.system.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountMapper accountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountMapper.selectOne(new LambdaQueryWrapper<Account>()
			.eq(Account::getUsername, username));
		if (account == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return AccountConverter.INSTANCE.toUserDetails(account);
	}

}
