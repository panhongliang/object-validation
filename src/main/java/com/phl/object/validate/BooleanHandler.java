package com.phl.object.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import ognl.Ognl;

import static ognl.Ognl.getValue;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class BooleanHandler implements Validate {

    public static final BooleanHandler instance=new BooleanHandler();

    public void validate(Object target, Object value, Method targetMethod, Annotation annotation) {
        if (annotation.annotationType() == Boolean.class) try {
            String express = (String) AnnotationUtil.value(annotation, "value");
            Object ognlValue = Ognl.getValue(express, target);
            if (ognlValue==null) {
                throw new IllegalArgumentException("not a boolean value ognl express:" + express);
            }

            if(ognlValue.getClass()==java.lang.Boolean.class){
                    boolean result=(java.lang.Boolean) ognlValue;
                    if(!result){
                        throw new  ValidateException("validated failed with express:"+express+", method:"+targetMethod.getName()+", value:"+value+" object:"+target);
                    }
            }else{
                    throw new IllegalArgumentException("not a boolean value ognl express:" + express);
            }

            if (!(ognlValue instanceof Boolean)) {
                throw new IllegalArgumentException("not a boolean value ognl express:" + express);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
