package com.example.framework.web.error;

import com.example.framework.web.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MaGuangZu
 * @since 2022-12-30
 */
@RestController
public class ErrorMappingController implements ErrorController {

    @RequestMapping("/error")
    public Result<Object> error() {
        return Result.error("System error");
    }

}
