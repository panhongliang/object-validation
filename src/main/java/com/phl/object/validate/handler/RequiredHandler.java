package com.phl.object.validate.handler;

import com.phl.object.validate.Constants;
import com.phl.object.validate.Validate;
import com.phl.object.validate.ValidateFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;


/**
 * Created by panhongliang  on 2016-06-20.
 */
public class RequiredHandler implements Validate {

	private RequiredHandler(){}
	public static RequiredHandler instance =new RequiredHandler();

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
