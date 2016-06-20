package com.phl.object.validate;

import com.phl.object.validate.annotation.Boolean;
import com.phl.object.validate.annotation.Regular;
import com.phl.object.validate.annotation.Required;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Obj {

    int a;
    String b;
    double c;
    String email;

    @Regular(Constants.Regular.EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Required
    @Boolean("getA()>3")
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Required
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
