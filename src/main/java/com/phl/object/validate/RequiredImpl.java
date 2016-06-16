package com.phl.object.validate;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;



public class RequiredImpl implements Validate {

	public static RequiredImpl instance =new  RequiredImpl();

	@SuppressWarnings("rawtypes")
	public void validate(Object value,Method m) {
		String tip=m.getName().substring(3)+"值不能为空";
		if(value==null ||value.toString().length()==0){
			throw new  IllegalArgumentException(tip);
		}
		if(Map.class.isAssignableFrom(value.getClass())){
			Map map=(Map)value;
			if(map.size()==0){
				throw new  IllegalArgumentException(tip);
			}
		}
		if(Collection.class.isAssignableFrom(value.getClass())){
			Collection collection=(Collection)value;
			if(collection.size()==0){
				throw new  IllegalArgumentException(tip);
			}
		}
	}

}
