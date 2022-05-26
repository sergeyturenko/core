package net.betvictor.core.utils;

import net.betvictor.core.dto.error.ErrorDescription;
import net.betvictor.core.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseUtils {

  public static ResponseEntity<ErrorResponse> badRequestResponse() {
    return buildErrorResponse(
        HttpStatus.BAD_REQUEST,
        ErrorDescription.REQUEST_ERROR_MESSAGE,
        ErrorDescription.REQUEST_ERROR_CODE);
  }

  public static ResponseEntity<ErrorResponse> badRequestResponse(String errorMessage) {
    return buildErrorResponse(
            HttpStatus.BAD_REQUEST,
            errorMessage,
            ErrorDescription.REQUEST_ERROR_CODE);
  }

  public static ResponseEntity<ErrorResponse> internalServerErrorResponse() {
    return buildErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR, ErrorDescription.INTERNAL_SERVER_ERROR_MESSAGE, null);
  }

  private static ResponseEntity<ErrorResponse> buildErrorResponse(
          HttpStatus httpStatus, String message, String stringCode) {
    return ResponseEntity.status(httpStatus.value()).body(new ErrorResponse(message, stringCode));
  }
}
