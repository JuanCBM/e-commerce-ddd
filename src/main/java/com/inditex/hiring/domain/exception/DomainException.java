package com.inditex.hiring.domain.exception;

class DomainException extends RuntimeException {

  DomainException(final String message) {
    super(message);
  }
}