package top.gzii.license.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.gzii.license.common.result.Result;
import top.gzii.license.common.result.ResultCodeEnum;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
   public Result handleCommonException(Exception e){

        e.printStackTrace();
        return new Result(ResultCodeEnum.INTERNAL_EXCEPTION);
   }

   @ExceptionHandler(LicenseException.class)
   public Result handleLicenseException(LicenseException licenseException){
        return new Result(licenseException.getCode(),licenseException.getMessage());
   }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleConstraintViolationException(ConstraintViolationException e) {

        return new Result(ResultCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 处理参数校验异常 - @Valid 注解校验请求体
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new Result(errors,ResultCodeEnum.PARAMETER_ERROR);
    }
}
