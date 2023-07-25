package com.example.bad;

import com.example.framework.common.util.CastUtil;
import com.example.framework.testsupport.BaseCase;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
class CastObjectTest extends BaseCase {

	@Test
	void testCast() {
		Map<String, String> map = new HashMap<>();
		CastObject castObject = new CastObject();
		castObject.setA1("a1");
		castObject.setB1("b1");
		// error
		Assertions.assertThrows(ClassCastException.class, () -> {
			map.put("a", CastUtil.safeFakeCast(castObject));
		});
	}

	@Setter
	@Getter
	static class CastObject {
		private String a1;
		private String b1;
	}

}
