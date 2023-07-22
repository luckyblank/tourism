package com.hzw.tourism.exception;

import com.hzw.tourism.comon.ResponseResult;
import com.hzw.tourism.constant.CommonConstants;

public class GlobalExceptionMyHandler extends RuntimeException {
    private static String customMessage;

    public GlobalExceptionMyHandler(String message) {
        super(message);
        this.customMessage = message;
    }
    public String getCustomMessage() {
        return customMessage;
    }

    public ResponseResult handleException() {
        return new ResponseResult(CommonConstants.FAIL, getCustomMessage());
    }

}
