package com.example.framework.common.options;

import lombok.*;

/**
 * @author MaGuangZu
 * @since 2023-08-02
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BenchUpdateOptions {

	private boolean updateIgnoreEquals;

	private static BenchUpdateOptions defaults = BenchUpdateOptions.builder()
		.build();

	public static BenchUpdateOptions defaults() {
		return defaults;
	}

}
