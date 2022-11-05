package com.inditex.hiring.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class NoSuchResourceFoundException extends RuntimeException {

  private static final long serialVersionUID = -5596141541624573125L;
}
