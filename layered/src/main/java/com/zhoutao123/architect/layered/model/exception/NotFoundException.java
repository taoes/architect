package com.zhoutao123.architect.layered.model.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends BaseBizException {

  public NotFoundException(ErrorCode errorCode, String... param) {
    super(errorCode, param);
  }
}
