package com.example.framework.web.it.error

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author MaGuangZu
 * @since 2023-09-08
 */
@Controller
class TestController {

	@GetMapping("/failed")
	fun failed(): String {
		@Suppress("DIVISION_BY_ZERO")
		1 / 0
		return "failed"
	}

}
