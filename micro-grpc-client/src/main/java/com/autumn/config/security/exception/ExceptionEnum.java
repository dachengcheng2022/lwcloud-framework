package com.autumn.config.security.exception;

public enum ExceptionEnum {
    USER_PASSWORD_ERR(100000,"用户名或密码错误！"),
    USER_NOT_REGISTER(100001,"用户未注册！"),
    USER_FROZEN(100003,"账户已冻结！"),
    VER_CODE_ERROR(100004,"验证码错误！"),
    VER_CODE_TYPE_ERROR(100005,"验证码类型错误！"),
    VER_CODE_INVALID(100006,"验证码已失效！"),
    USER_ONLY_WEIXIN(100007,"仅绑定微信ID！"),
    OTHER_CODE(100009,"未知错误！"),
    TOKEN_EXPIRES(100010,"请重新登录！"),

    VER_NAMEPSW_ERR(100020,"用户名或密码错误！"),

    VER_NEED_CAPACHA(100030,"错误次数过多，请输入图形验证码！"),
    VER_CAPACHA_ERR(100031,"图形验证码异常！"),
    USER_OFFLINE(100032,"您的账号已在其它设备登录，如果这不是您的操作，请及时修改密码。"),

    OCR_FAILED(101000,"活体校验失败"),
    OCR_IDCARD_ERR(101001,"身份证格式错误"),

    MAC_NEED_SMRZ(102000,"请先进行实名认证");
    
    private Integer code;
    private String value;

    ExceptionEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(Integer code){
        for (ExceptionEnum c : ExceptionEnum.values()) {
            if (c.getCode() == code) {
                return c.value;
            }
        }
        return null;
    }


    public String getCodeStr() {
        return code.toString();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
