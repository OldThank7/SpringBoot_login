package com.oldthank.utils.Result;

import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
@Getter
@Setter
@ApiModel(value = "返回结果实体类", description = "结果实体类")
public class Result implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "返回码")
  private Integer code;

  @ApiModelProperty(value = "返回消息")
  private String message;

  @ApiModelProperty(value = "返回数据")
  private Object data;

  private Result() {

  }

  public Result(ResultCode resultCode, Object data) {
    this.code = resultCode.code();
    this.message = resultCode.message();
    this.data = data;
  }

  private void setResultCode(ResultCode resultCode) {
    this.code = resultCode.code();
    this.message = resultCode.message();
  }

  // 返回成功
  public static Result success() {

    HttpStatus ok = HttpStatus.OK;

    Result result = new Result();
    result.setResultCode(ResultCode.SUCCESS);
    return result;
  }

  // 返回成功
  public static Result success(Object data) {
    Result result = new Result();
    result.setResultCode(ResultCode.SUCCESS);
    result.setData(data);
    return result;
  }

  // 返回失败
  public static Result fail(Integer code, String message) {
    Result result = new Result();
    result.setCode(code);
    result.setMessage(message);
    return result;
  }

  // 返回失败
  public static Result fail(ResultCode resultCode) {
    Result result = new Result();
    result.setResultCode(resultCode);
    return result;
  }

  @Override
  public String toString() {
    String string = JSON.toJSONString(super.toString());
    return string;
  }
}