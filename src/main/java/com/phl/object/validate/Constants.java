package com.phl.object.validate;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class Constants {
    public static class Regular{
        public static final String EMAIL="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
        public static final String NUMBER="^[0-9]*$";
        public static final String POSITIVE_INTEGER="^+?[1-9][0-9]*$";
        public static final String CHINESE_CHAR="^[\u4e00-\u9fa5]{1,}$";
    }
    public static class ValidateMsg {
        public static final String OGNL_FAIL = "校验失败，值: %s 不满足Ognl表达式: %s, 方法: %s  对象: %s";
        public static final String REGULAR_FAIL = "校验失败，正则表达式: %s, 方法: %s 值: %s 对象: %s";

    }
}
