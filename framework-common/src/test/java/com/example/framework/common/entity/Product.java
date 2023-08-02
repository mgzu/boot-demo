package com.example.framework.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

	private String id;
	private String name;

}
