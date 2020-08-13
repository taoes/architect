package com.zhoutao123.architect.layered.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class ErrorMessage {

  static final ErrorMessage EMPTY_MSG = new ErrorMessage();

  private final String msg;

  private final List<String> extraInfo;

  private ErrorMessage() {
    msg = null;
    extraInfo = null;
  }

  public ErrorMessage(String msg, List<String> extraInfo) {
    this.msg = msg;
    this.extraInfo = extraInfo;
  }

  public static ErrorMessage valueOf(Exception e) {
    List<String> stackInfo =
        Arrays.stream(e.getStackTrace())
            .map(StackTraceElement::toString)
            .collect(Collectors.toList());
    return new ErrorMessage(e.getMessage(), stackInfo);
  }
}
