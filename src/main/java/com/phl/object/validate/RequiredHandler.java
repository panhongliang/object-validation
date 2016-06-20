package com.phl.object.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * Created by panhongliang  on 2016-06-20.
 */
public class RequiredHandler implements Validate {

	public static RequiredHandler instance =new RequiredHandler();

	@SuppressWarnings("rawtypes")
	public void validate(Object target, Object value, Method m, Annotation annotation) {
		String tip=m.getName().substring(3)+" null value not allowed";
		if(value==null ||value.toString().length()==0){
			throw new  ValidateException(tip);
		}
		if(Map.class.isAssignableFrom(value.getClass())){
			Map map=(Map)value;
			if(map.size()==0){
				throw new  ValidateException(tip);
			}
		}
		if(Collection.class.isAssignableFrom(value.getClass())){
			Collection collection=(Collection)value;
			if(collection.size()==0){
				throw new  ValidateException(tip);
			}
		}
	}

}
