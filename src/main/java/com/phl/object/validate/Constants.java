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
    public static class ValidateMsg{
        public static final String FAIL="validated failed with express:%s, method:%s value:%s object:%s";
    }
}
