package com.hzw.tourism.comon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hzw.tourism.constant.CommonConstants;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    private Integer code;
    private String msg;
    private Long total;
    private Object data;

    public ResponseResult() {
    }


    public static ResponseResult isSuccess(boolean flag,String msg){
        return flag? new ResponseResult(200,msg+"成功"):new ResponseResult(400,msg+"失败");
    }

    public static ResponseResult fail(){
        return responseResult(CommonConstants.FAIL,"失败",CommonConstants.TOTAL,null);
    }
    public static ResponseResult fail(Object data){
        return responseResult(CommonConstants.FAIL,"失败",0L,data);
    }

    public static ResponseResult success(Object data){
        return responseResult(CommonConstants.SUCCESS,"成功",CommonConstants.TOTAL,data);
    }
    public static ResponseResult success(){
        return responseResult(CommonConstants.SUCCESS,"成功",CommonConstants.TOTAL,null);
    }
    //列表
    public static ResponseResult success(Object data, Long total){
        return responseResult(CommonConstants.SUCCESS,"成功",total,data);
    }

    private static ResponseResult responseResult(int code, String msg, Long total, Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(data);
        responseResult.setCode(code);
        responseResult.setMsg(msg);
        responseResult.setTotal(total);
        return responseResult;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



}
