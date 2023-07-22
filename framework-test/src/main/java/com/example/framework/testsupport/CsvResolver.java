package com.example.framework.testsupport;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.List;

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
		// 当前 parameterContext.getDeclaringExecutable() 获取到的是工厂方法，并未执行方法
//        Executable declaringExecutable = parameterContext.getDeclaringExecutable();
//        CsvPath annotation = declaringExecutable.getAnnotation(CsvPath.class);
//        return Optional.of(annotation)
//                .orElseThrow(() -> new IllegalArgumentException("测试方法必须添加 @CsvPath 注解"));
		String displayName = extensionContext.getDisplayName();
		if (!StrUtil.contains(displayName, StrPool.COLON)) {
			throw new IllegalArgumentException("@DisplayName 必须包含" + StrPool.COLON);
		}
		List<String> split = StrUtil.split(displayName, StrPool.COLON);
		return split.get(1);
	}

}
