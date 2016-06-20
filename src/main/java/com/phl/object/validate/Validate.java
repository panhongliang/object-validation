package com.phl.object.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
/**
 * Created by panhongliang  on 2016-06-20.
 */
public interface Validate {
	void validate(Object target, Object value, Method targetMethod, Annotation annotation);
}
