package com.example.framework.testsupport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2023-07-26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

	private String id;
	private String name;
	private List<String> stores;

}
