package com.zhoutao123.architect.layered.config;

import com.zhoutao123.architect.layered.config.ExceptionInfoConfig.I18NTemplate;
import com.zhoutao123.architect.layered.model.ErrorMessage;
import com.zhoutao123.architect.layered.model.Response;
import com.zhoutao123.architect.layered.model.exception.BaseBizException;
import com.zhoutao123.architect.layered.model.exception.ErrorCode;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

  public ExceptionAdvice() {
    System.out.println("ExceptionAdvice.ExceptionAdvice");
  }

  @ExceptionHandler(BaseBizException.class)
  public ResponseEntity<Response<ErrorMessage>> handleException(
      HttpServletRequest request, BaseBizException e) {
    ErrorCode errorCode = e.getErrorCode();
    log.info("出现业务异常:{}", errorCode);

    String i18nLanguage;
    String language = request.getHeader("client-language");
    if (StringUtils.hasText(language)) {
      i18nLanguage = language;
    } else {
      i18nLanguage = "Default";
    }
    String error =
        Optional.ofNullable(ExceptionInfoConfig.findByErrorCode(errorCode))
            .map(t -> t.getByName(i18nLanguage))
            .map(t -> String.format(t, e.getParam()))
            .orElse("未知异常");

    return new ResponseEntity<>(
        Response.error(new ErrorMessage(error, new ArrayList<>()), e), HttpStatus.OK);
  }
}
