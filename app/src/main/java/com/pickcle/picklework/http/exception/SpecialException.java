package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/1/17.
 */

public class SpecialException extends RuntimeException {
    private int returnCode;

    public SpecialException(int returnCode) {
        this.returnCode = returnCode;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
