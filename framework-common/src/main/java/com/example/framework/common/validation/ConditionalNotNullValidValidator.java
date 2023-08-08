package com.example.framework.common.validation;

import com.example.framework.common.annotations.ConditionalNotNull;
import com.example.framework.common.annotations.ConditionalNotNullValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.dromara.hutool.core.reflect.FieldUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author MaGuangZu
 * @since 2023-07-28
 */
public class ConditionalNotNullValidValidator implements ConstraintValidator<ConditionalNotNullValid, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		boolean isValid = true;
		Field[] fields = FieldUtil.getFields(value.getClass());
		if (fields.length > 0) {
			SpelExpressionParser parser = new SpelExpressionParser();
			Map<String, Object> map = new HashMap<>();
			for (Field field : fields) {
				ConditionalNotNull annotation = field.getAnnotation(ConditionalNotNull.class);
				if (annotation == null) {
					continue;
				}

				Expression expression = parser.parseExpression(annotation.value());
				if (Boolean.TRUE.equals(expression.getValue(value, Boolean.class)) &&
					Objects.isNull(FieldUtil.getFieldValue(value, field))) {
					map.put("fieldName", field.getName());
					// todo: Build messages using the validator api
					String messageTemplate = StrUtil.isBlank(annotation.message()) ? context.getDefaultConstraintMessageTemplate() : annotation.message();
					context
						.buildConstraintViolationWithTemplate(StrUtil.format(messageTemplate, map))
						.addConstraintViolation();
					isValid = false;
				}
			}
		}
		return isValid;
	}

}
