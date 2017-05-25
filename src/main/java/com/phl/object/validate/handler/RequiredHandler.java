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
	public void validate(Object target, Object value, Method m, Annotation annotation) {
		String tip=m.getName().substring(3)+" null value not allowed";
		if(value==null ||value.toString().length()==0){
			ValidateFactory.EXCEPTION=tip;
			return;
		}
		if(Map.class.isAssignableFrom(value.getClass())){
			Map map=(Map)value;
			if(map.size()==0){
				ValidateFactory.EXCEPTION=tip;
				return;
			}
		}
		if(Collection.class.isAssignableFrom(value.getClass())){
			Collection collection=(Collection)value;
			if(collection.size()==0){
				ValidateFactory.EXCEPTION=tip;
				return;
			}
		}
	}

}
