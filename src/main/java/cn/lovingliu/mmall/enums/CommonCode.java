package cn.lovingliu.mmall.enums;

/**
 * @Author：LovingLiu
 * @Description: 处理结果
 * @Date：Created in 2019-10-10
 */
public enum CommonCode {
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    AUTH_NOT_ALLOW(100,"权限不足"),
    AUTH_ERROR(101,"权限校验失败");

    private final int code;
    private final String msg;

    CommonCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
