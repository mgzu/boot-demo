package com.example.framework.common;

import com.example.framework.common.annotations.ConditionalNotNull;
import com.example.framework.common.annotations.ConditionalNotNullValid;
import com.example.framework.testsupport.BaseCase;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
public class ConditionalNotNullTest extends BaseCase {

	@Test
	void testSpEl() {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression1 = parser.parseExpression("#root.requiredPrice == true");
		Product product1 = Product.builder().requiredPrice(true).build();
		Boolean value1 = expression1.getValue(product1, Boolean.class);
		assertThat(value1).isTrue();
		Expression expression2 = parser.parseExpression("!(#root.requiredPrice == true)");
		Product product2 = Product.builder().requiredPrice(false).build();
		Boolean value2 = expression2.getValue(product2, Boolean.class);
		assertThat(value2).isTrue();
	}

	@Test
	void testValid() {
		Product product = Product.builder()
			.requiredPrice(true)
			.price2(1.0)
			.build();
		Set<ConstraintViolation<Product>> validate = validate(product);
		assertThat(validate).hasSize(2);
		validate
			.forEach(result -> assertThat(result.getMessage()).containsAnyOf("金额设置错误", "必填"));
	}

	@Test
	void testInvalid() {
		Product product = Product.builder()
			.price(1.0)
			.build();
		Set<ConstraintViolation<Product>> validate = validate(product);
		assertThat(validate).hasSize(1);
		validate
			.forEach(result -> assertThat(result.getMessage()).contains("金额设置错误"));
	}

	@ConditionalNotNullValid(message = "{fieldName} 金额设置错误")
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Builder
	static class Product {
		private Boolean requiredPrice;

		@ConditionalNotNull("#root.requiredPrice == true")
		private Double price;

		@ConditionalNotNull("!(#root.requiredPrice == true)")
		private Double price2;

		@ConditionalNotNull(value = "#root.requiredPrice == true", message = "{fieldName} 必填")
		private Double price3;

		private Double price4;

	}

}
