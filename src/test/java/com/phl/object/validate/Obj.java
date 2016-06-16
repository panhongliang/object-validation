package com.phl.object.validate;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Obj {

    int a;
    String b;
    double c;

    @Required
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
