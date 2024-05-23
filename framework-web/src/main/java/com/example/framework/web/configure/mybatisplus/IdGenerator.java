package com.example.framework.web.configure.mybatisplus;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.dromara.hutool.core.data.id.IdUtil;

/**
 * @author MaGuangZu
 * @since 2024-05-19
 */
public class IdGenerator implements IdentifierGenerator {

	@Override
	public Number nextId(Object entity) {
		return IdUtil.getSnowflakeNextId();
	}

}
