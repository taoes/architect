package com.zhoutao123.architect.layered.model;

import com.zhoutao123.architect.layered.model.exception.ErrorCode;
import lombok.Data;

@Data
public class Response<T> {

  private final ResponseStatus status;

  private final ErrorMessage msg;

  private final T data;

  public Response(ResponseStatus status, ErrorMessage msg, T data) {
    this.status = status;
    this.msg = msg;
    this.data = data;
  }

  public static <T> Response<T> ok(T data) {
    return new Response<>(ResponseStatus.OK, null, data);
  }

  public static <T> Response<T> error(ErrorMessage data, Exception e) {
    return new Response<>(ResponseStatus.ERROR, data, null);
  }

  public static <T> Response<T> reject(T data, String msg) {
    return new Response<>(ResponseStatus.OK, ErrorMessage.EMPTY_MSG, data);
  }

  enum ResponseStatus {
    OK,
    ERROR
  }
}
