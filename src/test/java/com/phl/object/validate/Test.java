package com.phl.object.validate;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Test {
    public static void main(String[] args) {
        Obj obj=new Obj();
        obj.setA(6);
        obj.setB("ab");
        obj.setEmail("panhl423@163.com");
        ValidateFactory.validate(obj);
    }
}
