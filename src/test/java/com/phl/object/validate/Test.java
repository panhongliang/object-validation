package com.phl.object.validate;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Test {
    @org.junit.Test
    public  void testObj() {
       long start= System.currentTimeMillis();
        for(int i=0;i<100;i++){
            Obj obj=new Obj();
            obj.setA(4);
            obj.setB("ab");
            obj.setEmail("panhl423@163.com");
            ValidateFactory.validate(obj);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
