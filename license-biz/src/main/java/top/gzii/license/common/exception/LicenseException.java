package top.gzii.license.common.exception;

import lombok.Data;
import top.gzii.license.common.result.ResultCodeEnum;

@Data
public class LicenseException extends RuntimeException{

    private Integer code;
    private String message;

        public LicenseException(Integer code,String message){
            this.code=code;
            this.message=message;
        }

        public LicenseException(ResultCodeEnum resultCodeEnum){
            this.code= resultCodeEnum.getCode();
            this.message=resultCodeEnum.getMessage();
        }

}
