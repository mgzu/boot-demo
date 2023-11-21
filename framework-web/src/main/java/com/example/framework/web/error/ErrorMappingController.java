package com.example.framework.web.error;

import com.example.framework.web.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaGuangZu
 * @since 2022-12-30
 */
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorMappingController extends AbstractErrorController {

	public ErrorMappingController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping
	public Result<Object> error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpStatus.OK.value());
		HttpStatus status = getStatus(request);
		return Result.builder()
			.code(status.value())
			.message(status.getReasonPhrase())
			.build();
	}

}
