package com.example.framework.web.constants;

import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author MaGuangZu
 * @since 2021-12-02
 */
@UtilityClass
public class WebConstants {

	public static final String TRACE_ID_KEY = "X-Trace-Id";

	public static final List<String> STATIC_RESOURCE_PATTERNS = List.of(
		"*.js", "*.css", "*.html", "*.jpg", "*.png", "*.gif", "*.ico", "*.ttf",
		"*.woff", "*.woff2", "*.svg", "*.eot"
	);

}
