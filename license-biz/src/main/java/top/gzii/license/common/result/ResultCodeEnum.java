package top.gzii.license.common.result;

public enum ResultCodeEnum {
    SUCCESS(1,"成功"),
    FAIL(2,"失败"),
    NO_LOGIN(3,"未登录"),

    NO_SUCH_USER(101,"用户不存在"),
    INCORRECT_USERNAME_PASSWORD(102,"用户名或密码错误"),
    USER_BANNED(103,"该账户已被禁用"),
    REPEAT_REGIST(104,"账户已存在"),


    INTERNAL_EXCEPTION(500,"服务器内部异常"),
    PARAMETER_ERROR(501,"参数异常"),
    DELETE_CHILD(502,"请先删除子表" );


    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
