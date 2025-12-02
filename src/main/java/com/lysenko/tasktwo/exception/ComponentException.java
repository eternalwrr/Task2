package com.lysenko.tasktwo.exception;

public class ComponentException extends Exception {
  public ComponentException(String message) {
    super(message);
  }
  public ComponentException(String message, Throwable cause) {
    super(message, cause);
  }
  public ComponentException(Throwable cause) {
    super(cause);
  }
}
