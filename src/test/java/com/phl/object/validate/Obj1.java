package com.phl.object.validate;

import com.phl.object.validate.annotation.Boolean;
import com.phl.object.validate.annotation.Regular;
import com.phl.object.validate.annotation.Validation;

/**
 * Created by Administrator on 2017-01-18.
 */
@Validation
public class Obj1 {
    private String strA;
    private int ia;
    private int ib;

    @Boolean("ia>10")
    public int getIa() {
        return ia;
    }

    public void setIa(int ia) {
        this.ia = ia;
    }

    @Boolean("ib>ia")
    public int getIb() {
        return ib;
    }

    public void setIb(int ib) {
        this.ib = ib;
    }

    @Boolean("strA.length>10")
    public String getStrA() {
        return strA;
    }

    public void setStrA(String strA) {
        this.strA = strA;
    }


}
