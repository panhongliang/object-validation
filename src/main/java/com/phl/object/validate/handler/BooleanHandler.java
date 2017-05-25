package com.phl.object.validate.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.phl.object.validate.ValidateFactory;
import com.phl.object.validate.util.AnnotationUtil;
import com.phl.object.validate.Constants;
import com.phl.object.validate.Validate;
import com.phl.object.validate.annotation.Boolean;
import ognl.Ognl;
import ognl.OgnlException;

import static ognl.Ognl.getValue;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class BooleanHandler implements Validate {

    private BooleanHandler(){}

    public static final BooleanHandler instance=new BooleanHandler();

    public void validate(Object target, Object value, Method targetMethod, Annotation annotation) {
        if (annotation.annotationType() == Boolean.class){
            String express = (String) AnnotationUtil.value(annotation, "value");
            Object ognlValue = null;
            try {
                ognlValue = Ognl.getValue(express, target);
            } catch (OgnlException e) {
                ValidateFactory.EXCEPTION=e.getMessage();
                return;
            }
            if (ognlValue==null) {
                ValidateFactory.EXCEPTION="not a boolean value ognl express:" + express;
                return;
            }

            if(ognlValue.getClass()==java.lang.Boolean.class){
                    boolean result=(java.lang.Boolean) ognlValue;
                    if(!result){
                        ValidateFactory.EXCEPTION=String.format(Constants.ValidateMsg.FAIL,express,targetMethod.getName(),value,target);
                        return;
                    }
            }else{
                ValidateFactory.EXCEPTION="not a boolean value ognl express:" + express;
                return;
            }

        }
    }
}
