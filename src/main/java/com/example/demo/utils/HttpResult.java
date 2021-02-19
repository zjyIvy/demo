package com.example.demo.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:一个http响应结果通用类
 */
public class HttpResult extends Throwable {

    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 请求成功时的响应结果
     *
     * @return
     */
    public static HttpResult success() {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(200);
        httpResult.setMsg("SUCCESS");
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     *
     * @param msg 自定义响应内容
     * @return
     */
    public static HttpResult success(String msg) {
        HttpResult httpResult = new HttpResult();
        httpResult.setMsg(msg);
        httpResult.setCode(200);
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     *
     * @param data 要响应的数据
     * @return
     */
    public static HttpResult success(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(200);
        httpResult.setMsg("SUCCESS");
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 请求失败时的响应内容
     *
     * @return
     */
    public static HttpResult error() {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(500);
        httpResult.setMsg("soething is wrong");
        return httpResult;
    }

    /**
     * 请求失败时的相应内容
     *
     * @param msg 自定义相应内容
     * @return
     */
    public static HttpResult error(String msg) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(500);
        httpResult.setMsg(msg);
        return httpResult;
    }

    /**
     * 请求成功时的响应结果
     *
     * @param code  自定义响应的内容
     * @param data 自定义响应数据
     * @return
     */
    public static HttpResult error(Integer code, Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(code);
        httpResult.setMsg("操作失败");
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 使用response输出JSON
     * @Param  resultMap 数据
     */
    public static void responseJson(ServletResponse response, Map<String, Object> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception e) {

        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 通用示例
     * @Param  code 信息码
     * @Param  msg  信息
     * @Return Map<String,Object> 返回数据MAP
     */
    public static Map<String, Object> resultCode(Integer code,String msg){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg",msg);
        resultMap.put("code",code);
        return resultMap;
    }

}
