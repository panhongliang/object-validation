package com.phl.object.validate.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.phl.object.validate.util.AnnotationUtil;
import com.phl.object.validate.Constants;
import com.phl.object.validate.Validate;
import com.phl.object.validate.ValidateException;
import com.phl.object.validate.annotation.Boolean;
import ognl.Ognl;
import ognl.OgnlException;

import static ognl.Ognl.getValue;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class BooleanHandler implements Validate {

    public static final BooleanHandler instance=new BooleanHandler();

    public void validate(Object target, Object value, Method targetMethod, Annotation annotation) {
        if (annotation.annotationType() == Boolean.class){
            String express = (String) AnnotationUtil.value(annotation, "value");
            Object ognlValue = null;
            try {
                ognlValue = Ognl.getValue(express, target);
            } catch (OgnlException e) {
                throw new ValidateException(e);
            }
            if (ognlValue==null) {
                throw new ValidateException("not a boolean value ognl express:" + express);
            }

            if(ognlValue.getClass()==java.lang.Boolean.class){
                    boolean result=(java.lang.Boolean) ognlValue;
                    if(!result){
                        throw new  ValidateException(String.format(Constants.ValidateMsg.FAIL,express,targetMethod.getName(),value,target));
                    }
            }else{
                    throw new ValidateException("not a boolean value ognl express:" + express);
            }

        }
    }
}
