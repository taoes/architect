package com.zhoutao123.architect.layered.model.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseBizException extends RuntimeException {

  private ErrorCode errorCode;


  private Object[] param;

  public BaseBizException(ErrorCode errorCode, String[] param) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.param = param;
  }
}
