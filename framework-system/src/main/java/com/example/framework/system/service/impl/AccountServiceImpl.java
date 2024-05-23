package com.example.framework.system.service.impl;

import com.example.framework.system.mapper.AccountMapper;
import com.example.framework.system.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountMapper accountMapper;

}
