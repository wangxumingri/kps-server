package com.wxss.kps.admin.exception;

import com.wxss.exception.CoreException;
import com.wxss.http.EnumResponseCode;
import com.wxss.http.ResponseBuilder;
import com.wxss.http.ResponseVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 异常处理器：
 *  需要加入日志
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理系统核心异常
     * @param e
     * @return
     */
    @ExceptionHandler(CoreException.class)
    public ResponseVo<Object> handlerCoreException(CoreException e){
        return ResponseBuilder.fail(e.getErrorCode(),e.getMessage());
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = EnumResponseCode.VALIDATE_ERROR.getMessage();
        String errorField = "";
        if (bindingResult.getFieldError() != null){
            errorMsg = bindingResult.getFieldError().getDefaultMessage();
            errorField = bindingResult.getFieldError().getField();
        }
//        RequestVo request = (RequestVo) bindingResult.getTarget();
        return ResponseBuilder.fail(EnumResponseCode.VALIDATE_ERROR.getCode(),errorMsg);
    }


    /**
     * 处理未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseVo<Object>  exception(Exception e) {
        return ResponseBuilder.fail();
    }
}
