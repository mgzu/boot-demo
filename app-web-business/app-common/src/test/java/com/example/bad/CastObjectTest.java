package com.example.bad;

import com.example.framework.common.util.CastUtil;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
class CastObjectTest {

	@Test
	void testCast() {
		Map<String, Object> map = new HashMap<>();
		CastObject castObject = new CastObject();
		castObject.setA1("a1");
		castObject.setB1("b1");
		map.put("a", castObject);
		// fake generic
		Map<String, String> map2 = CastUtil.safeFakeCast(map);
		map2.put("1", "2");
		assertThat(map2).hasSize(2);
	}

	@Setter
	@Getter
	static class CastObject {
		private String a1;
		private String b1;
	}

}
