package me.geso.tinyvalidator.rules;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.geso.tinyvalidator.Rule;
import me.geso.tinyvalidator.constraints.Pattern;

public class PatternRule implements Rule {
	private static Logger logger = LoggerFactory.getLogger(PatternRule.class);

	@Override
	public boolean validate(Object root, Object target, List<String> route,
			String name, Annotation annotation, Object fieldValue) {
		if (fieldValue instanceof String) {
			Pattern pattern = (Pattern) annotation;
			java.util.regex.Pattern compiled = java.util.regex.Pattern
					.compile(pattern.regexp());
			Matcher matcher = compiled.matcher((String) fieldValue);
			return matcher.find();
		} else {
			logger.warn("You shouldn't set @Pattern for non String field/getter.");
			return false;
		}
	}

}
