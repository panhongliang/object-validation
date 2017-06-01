package com.phl.object.validate.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.phl.object.validate.Constants;
import com.phl.object.validate.Validate;
import com.phl.object.validate.util.AnnotationUtil;
import ognl.Ognl;
import ognl.OgnlException;

/**
 * Created by panhongliang on 2016-06-20.
 */
public class BooleanHandler implements Validate {

    private BooleanHandler(){}

    public static final BooleanHandler instance = new BooleanHandler();

    public String validate(Object target, Object value, Method targetMethod, Annotation annotation) {

        String express = (String) AnnotationUtil.value(annotation, "value");
        Object ognlValue = null;
        try {
            ognlValue = Ognl.getValue(express, target);
        } catch (OgnlException e) {
            return "值:"+value+",不满足ognl表达式:"+express+",异常信息："+e.getMessage();
        }
        if (ognlValue == null) {
            return "ognl表达式值不是boolean类型:" + express;
        }

        if (ognlValue.getClass() == java.lang.Boolean.class) {
            boolean result = (java.lang.Boolean) ognlValue;
            if (!result) {
                return String.format(Constants.ValidateMsg.OGNL_FAIL,value, express, targetMethod.getName(), target);
            }
        } else {
            return "ognl表达式值不是boolean类型:" + express;
        }
        return  null;
    }
}
