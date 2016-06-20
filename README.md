# object-validation

对象值校验，支持非空校验（@Required）,正则表达式校验(@Regular(正则表达式)),Ognl表达式校验（@Boolean(Ognl表达式)），可以满足大部分需
求，不满足需求的情况下可以自己扩展。支持扩展和覆盖默认实现。
校验方法：在类的get方法上打上相应的注释，然后调用  ValidateFactory.validate(对象)即可;
如：有类Obj，

public class Obj {
    private int a;
    private String b;
    private double c;
    private String email;

    @Regular(Constants.Regular.EMAIL) //要满足邮箱格式
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Required //不能为空
    @Boolean("a>3 && a<5") //a的值要>3并且<5 ognl表达式
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Boolean("b!=null && b.length()<3") //b的值不能为空，且长度小于3 ognl表达式
    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Required//非空
    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}


测试类：

public class Test {
    public static void main(String[] args) {
        Obj obj=new Obj();
        obj.setA(6);//a值不满足条件，会抛出非检查性异常
        obj.setB("ab");
        obj.setEmail("panhl423@163.com");
        ValidateFactory.validate(obj);
    }
}

------------------------------运行结果------------------------------------------------

六月 20, 2016 2:00:39 下午 com.phl.object.validate.ValidateFactory <clinit>
警告: no extra validate.properties file found in class path
com.phl.object.validate.ValidateException: validated failed with express: a>3 && a<5, method: getA value: 6 object: com.phl.object.validate.Obj@5152441a
	at com.phl.object.validate.handler.BooleanHandler.validate(BooleanHandler.java:39)
	at com.phl.object.validate.ValidateFactory.validate(ValidateFactory.java:115)
	at com.phl.object.validate.Test.main(Test.java:12)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)

