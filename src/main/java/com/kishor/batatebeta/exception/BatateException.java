package com.kishor.batatebeta.exception;

/**
 * Created by Nandakishor on 8/14/2015.
 */
public class BatateException extends Exception {

    public BatateException(Exception exception)
    {
        super(exception);
    }

    public BatateException(String exception)
    {
       super(exception);
    }

    @Override
    public Throwable getCause()
    {
       return super.getCause();
    }

    @Override
    public StackTraceElement[] getStackTrace()
    {
        return super.getStackTrace();
    }
}
