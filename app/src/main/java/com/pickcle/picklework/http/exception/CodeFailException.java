package com.pickcle.picklework.http.exception;

/**
 * Created by justcan on 2018/3/28.
 */

public class CodeFailException extends RuntimeException {
    public CodeFailException(String detailMessage) {
        super(detailMessage);
    }
}
