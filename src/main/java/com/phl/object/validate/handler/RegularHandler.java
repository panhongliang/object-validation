package com.phl.object.validate.handler;

import com.phl.object.validate.util.AnnotationUtil;
import com.phl.object.validate.Constants;
import com.phl.object.validate.Validate;
import com.phl.object.validate.ValidateException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class RegularHandler implements Validate {

    public static final RegularHandler instance=new RegularHandler();

    public void validate(Object target, Object value, Method targetMethod, Annotation annotation) {

            String patternStr= (String) AnnotationUtil.value(annotation,"value");
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher((CharSequence) value);
            if(matcher.find()){
                return;
            }else {
                throw new ValidateException(String.format(Constants.ValidateMsg.FAIL,pattern,targetMethod.getName(),value,target));
            }

    }
}
