package top.gzii.license.common.result;

import lombok.Data;

@Data
public class Result<T>  {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T data, ResultCodeEnum resultCodeEnum){
        this.code= resultCodeEnum.getCode();
        this.message=resultCodeEnum.getMessage();
        this.data=data;
    }

    public Result(ResultCodeEnum resultCodeEnum){
        this.code= resultCodeEnum.getCode();
        this.message=resultCodeEnum.getMessage();
    }
    public static Result success(){
        return new Result(ResultCodeEnum.SUCCESS);

    }

    public  static <T> Result<T> success(T data){
        return new Result<T>(data,ResultCodeEnum.SUCCESS);
    }



    public static Result fail(){
        return new Result(ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>(data, ResultCodeEnum.FAIL);
    }


}
