package com.phl.object.validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Test {
    @org.junit.Test
    public  void testObj() {
       long start= System.currentTimeMillis();
            Obj obj=new Obj();
            obj.setA(4);
            obj.setB("ab");
            obj.setEmail("panhl423@163.com");
            Map<String ,Obj1> map=new HashMap<String, Obj1>();
            Obj1 obj1=new Obj1();
            obj1.setStrA("aaazzzzzzza");
            obj1.setIa(11);
            obj1.setIb(9);
             map.put("map",obj1);
            obj.setMap(map);
            ValidateFactory.validate(obj);

        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
