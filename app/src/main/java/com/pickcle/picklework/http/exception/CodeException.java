package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/3/28.
 */

public class CodeException extends RuntimeException {
    public CodeException(String detailMessage) {
        super(detailMessage);
    }
}
