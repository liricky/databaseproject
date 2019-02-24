package cn.liricky.database.model.vo;

import lombok.Data;

/**
 * @program: dbhk
 * @description: 返回json标准格式
 * @author: 0GGmr0
 * @create: 2019-01-18 12:56
 */
@Data
public class Result<T> {
    /**
     * 标识码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
