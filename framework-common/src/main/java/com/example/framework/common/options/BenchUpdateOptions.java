package com.example.framework.common.options;

import lombok.*;

/**
 * @author MaGuangZu
 * @since 2023-08-02
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Setter
@Getter
public class BenchUpdateOptions {

	/**
	 * Update operations ignore equals objects (class should override the equals method)
	 */
	private boolean updateIgnoreEquals;

	private static BenchUpdateOptions defaults = BenchUpdateOptions.builder()
		.updateIgnoreEquals(true)
		.build();

	public static BenchUpdateOptions defaults() {
		return defaults;
	}

}
