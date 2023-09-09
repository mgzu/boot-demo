package com.example.framework.web.it.error

import com.example.framework.common.exceptions.ServiceException
import com.example.framework.web.entity.Result
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author MaGuangZu
 * @since 2023-09-08
 */
@Controller
class TestController {

	@GetMapping("/failed")
	fun failed(): String {
		throw ServiceException("failed")
	}

	@ResponseBody
	@GetMapping("/failed2")
	fun failed2(): Result<Void> {
		throw ServiceException("failed")
	}

}
