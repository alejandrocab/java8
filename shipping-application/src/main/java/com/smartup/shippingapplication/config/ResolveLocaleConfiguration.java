package com.smartup.shippingapplication.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class ResolveLocaleConfiguration extends AcceptHeaderLocaleResolver {

	List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("es"));
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		if (StringUtils.isEmpty(request.getHeader("Accept-Language"))) {
			return new Locale("es");
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
		Locale locale = Locale.lookup(list, LOCALES);
		return locale;
	}
}
