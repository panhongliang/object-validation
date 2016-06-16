package com.phl.object.validate;

import java.lang.reflect.Method;

public interface Validate {

	void validate(Object value,Method targetMethod);
}
