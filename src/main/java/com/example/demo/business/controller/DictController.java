package com.example.demo.business.controller;

import com.example.demo.framework.Dict;
import com.example.demo.framework.Result;
import com.example.demo.framework.util.DictUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-09-27
 */
@RequestMapping("dict")
@RestController
public class DictController extends BaseController {

    List<Dict> dicts = new ArrayList<>();

    @PostMapping
    public Result<Dict> add(@Validated @RequestBody Dict dict) {
        dicts.add(dict);
        return Result.ok();
    }

    @GetMapping
    public Result<Object> test() {
        return Result.ok(DictUtil.typeConvert(dicts));
    }
}
