package net.betvictor.core.exception;

public class ServiceUnavailableException extends RuntimeException {
  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }
}
