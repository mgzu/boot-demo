package com.example.fsm.business.controller;

import com.example.framework.Dict;
import com.example.framework.Result;
import com.example.framework.util.DictUtil;
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
