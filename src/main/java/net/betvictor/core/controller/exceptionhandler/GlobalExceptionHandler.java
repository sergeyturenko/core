package net.betvictor.core.controller.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import net.betvictor.core.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static net.betvictor.core.utils.ErrorResponseUtils.badRequestResponse;
import static net.betvictor.core.utils.ErrorResponseUtils.internalServerErrorResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException e) {
        log(e);
        return internalServerErrorResponse();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationError(MethodArgumentNotValidException e) {
        log(e);
        return badRequestResponse();
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> validationError(RuntimeException e) {
        log(e);
        return badRequestResponse(e.getMessage());
    }

    private void log(Exception e) {
        log.warn(
                "Exception while processing REST call: {}. "
                        + "To see full stacktrace enable DEBUG logging level.",
                e.getMessage());
        log.debug("Exception details: ", e);
    }
}
