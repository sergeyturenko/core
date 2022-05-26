package net.betvictor.core.dto.error;

/** Error response description. */
public class ErrorDescription {
  public static final String REQUEST_ERROR_MESSAGE =
      "One of the parameters is missing or is invalid";
  public static final String REQUEST_ERROR_CODE = "REQUEST_ERROR";

  public static final String INTERNAL_SERVER_ERROR_MESSAGE =
      "Unexpected internal server error when processing the request";
}
