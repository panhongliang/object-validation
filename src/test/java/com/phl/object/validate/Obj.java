package com.phl.object.validate;

import com.phl.object.validate.annotation.Boolean;
import com.phl.object.validate.annotation.Regular;
import com.phl.object.validate.annotation.Required;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Obj {

    private int a;
    private String b;
    private double c;
    private String email;

    @Regular(Constants.Regular.EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Required
    @Boolean("a>3 && a<5")
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Boolean("b!=null && b.length()<3")
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Required
    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
