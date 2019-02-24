package cn.liricky.database.tools;

import cn.liricky.database.model.vo.Result;

import java.util.List;

/**
 * @program: dbhk
 * @description: 返回json数据格式的工具类
 * @author: 0GGmr0
 * @create: 2019-01-18 12:56
 */
public class ResultTool {

    @SuppressWarnings("unchecked")
    public static Result success(List<Object> object){
        Result result = new Result();
        result.setCode(200);
        result.setData(object);
        return result;
    }


    @SuppressWarnings("unchecked")
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(200);
        result.setData(object);
        return result;
    }


    public static Result success(){
        Result result = new Result();
        result.setCode(200);
        return result;
    }


    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }


    public static Result error(Integer code){
        Result result = new Result();
        result.setCode(code);
        return result;
    }
}
