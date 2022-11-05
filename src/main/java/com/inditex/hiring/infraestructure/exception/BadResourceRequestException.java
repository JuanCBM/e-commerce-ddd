package com.inditex.hiring.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class BadResourceRequestException extends RuntimeException {

  public BadResourceRequestException(String msg) {
    super(msg);
  }
}
