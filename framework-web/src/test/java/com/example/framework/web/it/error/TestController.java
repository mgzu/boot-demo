package com.example.framework.web.it.error;

import com.example.framework.common.exceptions.ServiceException;
import com.example.framework.web.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
@Controller
public class TestController {
	@GetMapping("/failed")
	public String failed() {
		throw new ServiceException("failed");
	}

	@ResponseBody
	@GetMapping("/failed2")
	public Result<Void> failed2() {
		throw new ServiceException("failed");
	}

	@ResponseBody
	@PostMapping("/failed3")
	public Result<Void> failed3() {
		return Result.ok();
	}

}
