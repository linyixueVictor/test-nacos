package org.example.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.common.consts.AppHttpCodeEnum;
import org.example.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //控制器增强类
@Slf4j
public class ExceptionCatch {

    /**
     * 处理不可控异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e){
        e.printStackTrace();
        log.error("catch exception:{}",e.getMessage());
        return R.errorResult(AppHttpCodeEnum.ERROR);
    }

    /**
     * 处理可控异常  自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R exception(CustomException e){
        e.printStackTrace();
        log.error("catch exception:{}",e);
        return R.errorResult(e.getAppHttpCodeEnum());
    }
}
