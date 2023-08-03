package com.example.app.waybill.controller;

import com.example.framework.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.data.id.IdUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @author MaGuangZu
 * @since 2021-12-02
 */
@Slf4j
@RequestMapping("waybill")
@RestController
public class WaybillController extends BaseController {

	@GetMapping
	public void get() {
		String id = IdUtil.simpleUUID();
		log.info(id);
	}

	@PostMapping
	public void add() {
		String id = IdUtil.simpleUUID();
		log.info(id);
	}

	@PutMapping
	public void update() {
		String id = IdUtil.simpleUUID();
		log.info(id);
	}

	@DeleteMapping
	public void remove() {
		String id = IdUtil.simpleUUID();
		log.info(id);
	}

}
