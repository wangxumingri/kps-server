package com.wxss.kps.common.exception;

import com.wxss.exception.CoreException;
import com.wxss.http.EnumResponseCode;

public class ServiceException extends CoreException {

    public ServiceException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ServiceException(String message) {
        super(message);
        this.setErrorCode(EnumResponseCode.SERVICE_ERROR.getCode());
    }

    public ServiceException(Exception e){
        super(e);
    }
}
