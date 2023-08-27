package com.example.framework.system.service.impl;

import com.example.framework.system.repository.UserRepository;
import com.example.framework.system.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

}
