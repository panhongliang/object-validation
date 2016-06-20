package com.phl.object.validate.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class AnnotationUtil {
    public static Object value(Annotation an,String strMth)  {
        try {
            Method method=an.annotationType().getMethod(strMth);
            return method.invoke(an);
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }
}
