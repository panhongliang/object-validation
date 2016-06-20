package com.phl.object.validate;

/**
 * Created by panhongliang  on 2016-06-20.
 */
public class ValidateException extends RuntimeException {
    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }
    public ValidateException(Throwable cause) {
        super(cause);
    }

}
