package com.phl.object.validate;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by panhongliang  on 2016-06-20.
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ValidateFactory {

	private static Map<Class, Validate> VALIDATE_HANDLER =new HashMap<Class, Validate>();
	private static Logger logger = Logger.getLogger(ValidateFactory.class.getName());

	static{
		VALIDATE_HANDLER.put(Required.class, RequiredHandler.instance);
		VALIDATE_HANDLER.put(Boolean.class,BooleanHandler.instance);
		VALIDATE_HANDLER.put(Regular.class,RegularHandler.instance);
		Properties props=new Properties();
		try {
			InputStream input= ValidateFactory.class.getClassLoader().getResourceAsStream("validate.properties");
			if(input==null){
				logger.log(Level.WARNING, "no extra validate.properties file found in class path");

			}else {
				props.load(input);
				Iterator it=props.keySet().iterator();
				while(it.hasNext()){
					String key=(String) it.next();
					String value=props.getProperty(key);
					try {
						VALIDATE_HANDLER.put(Class.forName(key), (Validate) Class.forName(value).newInstance());
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 集合校验
	 * @param collection
	 */
	public static <E> void validate(Collection<E> collection){
		Iterator<E> it=collection.iterator();
		while(it.hasNext()){
			E e=it.next();
			if(Map.class.isAssignableFrom(e.getClass())){
				validate((Map)e);
			}
			if(Collection.class.isAssignableFrom(e.getClass())){
				validate((Collection)e);
			}else{
				validate(e);
			}
		}
	}
	
	 /**
	  * Map校验
	  * @param map
	  */
	public static <K,V> void validate(Map<K,V> map){
		Iterator<K> it=map.keySet().iterator();
		while(it.hasNext()){
			V v=map.get(it.next());
			if(Map.class.isAssignableFrom(v.getClass())){
				validate((Map)v);
			}
			if(Collection.class.isAssignableFrom(v.getClass())){
				validate((Collection)v);
			}else{
				validate(v);
			}
		}
	}
	/**
	 * 对象校验
	 * @param obj
	 */
	public static void validate(Object obj) {
		Method[] ms = obj.getClass().getMethods();
		for (int i = 0, len = ms.length; i < len  ; i++) {
			//get方法
			if(!ms[i].getName().startsWith("get")){continue;}

			List<Annotation> list = getValidates(ms[i]);
			try {
			if (list.size() > 0) {
				Object value  = ms[i].invoke(obj);
				Class returnType=ms[i].getReturnType();
				for (Annotation an : list) {
					Validate validate = VALIDATE_HANDLER.get(an.annotationType());
					validate.validate(obj,value, ms[i],an);
				}
				//如果方法返回值不是8种基本类型及包装类及String类型，还要对返回的值进一步校验
				
				//判断返回类型是否是集合
				if(Collection.class.isAssignableFrom(returnType)){
					validate((Collection)value);
				}
				if(Map.class.isAssignableFrom(returnType)){
					validate((Map)value);
				}
				//如果返回的不是8种基础类型及包装类及String
				if(!returnType.isPrimitive() && !isPrimitiveWrapClass(returnType) && !String.class.equals(returnType)){
					validate(value);
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 获取方法上的校验注解
	 * @param m
	 * @return
	 */
	private static List<Annotation> getValidates(Method m) {
		List<Annotation> list = new ArrayList<Annotation>();
		Annotation[] ans = m.getAnnotations();
		for (Annotation an : ans) {
			if (an.annotationType() != null && an.annotationType().getAnnotation(Validation.class) != null) {
				list.add(an);
			}
		}
		return list;
	}
	
	/**
	 * 是否是8种基本类型的包装类
	 * @param clz
	 * @return
	 */
	private static boolean isPrimitiveWrapClass(Class<?> clz) {
	        try {
	           return ((Class<?>) clz.getField("TYPE").get(null)).isPrimitive();
	        } catch (Exception e) {
	            return false;
	        }
	    }

}
