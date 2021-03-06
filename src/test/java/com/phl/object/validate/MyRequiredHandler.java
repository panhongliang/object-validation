package com.phl.object.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * Created by panhongliang  on 2016-06-20.
 * Required的实现，覆盖默认的实现
 */
public class MyRequiredHandler implements Validate {

	public static MyRequiredHandler instance =new MyRequiredHandler();

	@SuppressWarnings("rawtypes")
	public String validate(Object target, Object value, Method m, Annotation annotation) {
		String tip=m.getName().substring(3)+" 不允许空值";
		if(value==null ||value.toString().length()==0){
			return tip;
		}
		if(Map.class.isAssignableFrom(value.getClass())){
			Map map=(Map)value;
			if(map.size()==0){
				return tip;
			}
		}
		if(Collection.class.isAssignableFrom(value.getClass())){
			Collection collection=(Collection)value;
			if(collection.size()==0){
				return tip;
			}
		}
		return null;
	}

}
