package com.example.framework.testsupport;

import org.dromara.hutool.core.text.StrUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author MaGuangZu
 * @since 2023-07-06
 */
public class CsvResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().getType() == String.class;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		// https://github.com/junit-team/junit5/issues/3323
		// 当前 parameterContext.getDeclaringExecutable() 获取到的是工厂方法，并非测试方法
//        Executable declaringExecutable = parameterContext.getDeclaringExecutable();
//        CsvPath annotation = declaringExecutable.getAnnotation(CsvPath.class);
//        return Optional.of(annotation)
//                .orElseThrow(() -> new IllegalArgumentException("测试方法必须添加 @CsvPath 注解"));
		String displayName = extensionContext.getDisplayName();
		if (!StrUtil.contains(displayName, StrUtil.COLON)) {
			throw new IllegalArgumentException("@DisplayName 必须包含" + StrUtil.COLON);
		}
		String[] split = StringUtils.split(displayName, StrUtil.COLON);
		Objects.requireNonNull(split);
		return split[1];
	}

}
