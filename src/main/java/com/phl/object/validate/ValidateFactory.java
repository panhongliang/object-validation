package com.phl.object.validate;

import com.phl.object.validate.annotation.Regular;
import com.phl.object.validate.annotation.Boolean;
import com.phl.object.validate.annotation.Required;
import com.phl.object.validate.annotation.Validation;
import com.phl.object.validate.handler.BooleanHandler;
import com.phl.object.validate.handler.RegularHandler;
import com.phl.object.validate.handler.RequiredHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by panhongliang on 2016-06-20.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ValidateFactory {

	private static Map<Class, Validate> VALIDATE_HANDLER = new ConcurrentHashMap<Class, Validate>();

	static {
		VALIDATE_HANDLER.put(Required.class, RequiredHandler.instance);
		VALIDATE_HANDLER.put(Boolean.class, BooleanHandler.instance);
		VALIDATE_HANDLER.put(Regular.class, RegularHandler.instance);
	}

	/**
	 * 集合校验
	 *
	 * @param collection
	 */
	public static <E> String validate(Collection<E> collection) {
		Iterator<E> it = collection.iterator();
		String exception=null;
		while (it.hasNext()) {
			E e = it.next();
			if (Map.class.isAssignableFrom(e.getClass())) {
				exception=validate((Map) e);
				if(exception!=null){
					return exception;
				}
			}
			if (Collection.class.isAssignableFrom(e.getClass())) {
				exception=validate((Collection) e);
				if(exception!=null){
					return exception;
				}
			} else {
				exception=validate(e);
				if(exception!=null){
					return exception;
				}
			}
		}
		return null;
	}

	/**
	 * Map校验
	 *
	 * @param map
	 */
	public static <K, V> String validate(Map<K, V> map) {
		String exception=null;
		Iterator<K> it = map.keySet().iterator();
		while (it.hasNext()) {
			V v = map.get(it.next());
			if (Map.class.isAssignableFrom(v.getClass())) {
				exception=validate((Map) v);
				if(exception!=null){
					return  exception;
				}
			}
			if (Collection.class.isAssignableFrom(v.getClass())) {
				validate((Collection) v);
				if(exception!=null){
					return  exception;
				}
			} else {
				exception=validate(v);
				if(exception!=null){
					return  exception;
				}
			}
		}
		return null;
	}

	/**
	 * 对象校验
	 *
	 * @param obj
	 */
	public static String validate(Object obj) {
		String exception=null;
		Method[] ms = obj.getClass().getMethods();
		for (int i = 0, len = ms.length; i < len; i++) {
			// get方法
			if (!ms[i].getName().startsWith("get")) {
				continue;
			}
			List<Annotation> list = getValidates(ms[i]);

			if (list.size() > 0) {
				Object value = null;
				try {
					value = ms[i].invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Class returnType = ms[i].getReturnType();
				for (Annotation an : list) {
					Validate validate = VALIDATE_HANDLER.get(an.annotationType());
					if (validate == null) {
						return "没有找到" + an.annotationType() + "的处理类";

					}
					exception=validate.validate(obj, value, ms[i], an);
					if(exception!=null){
						return exception;
					}

				}
				// 如果方法返回值不是8种基本类型及包装类及String类型，还要对返回的值进一步校验

				// 判断返回类型是否是集合
				if (Collection.class.isAssignableFrom(returnType)) {
					exception= validate((Collection) value);
					if(exception!=null){
						return exception;
					}
				}
				if (Map.class.isAssignableFrom(returnType)) {
					exception= validate((Map) value);
					if(exception!=null){
						return exception;
					}
				}
				// 如果返回的不是8种基础类型及包装类及String
				if (!returnType.isPrimitive() && !isPrimitiveWrapClass(returnType)
						&& !String.class.equals(returnType)) {
					exception= validate(value);
					if(exception!=null){
						return exception;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取方法上的校验注解
	 *
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
	 *
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
