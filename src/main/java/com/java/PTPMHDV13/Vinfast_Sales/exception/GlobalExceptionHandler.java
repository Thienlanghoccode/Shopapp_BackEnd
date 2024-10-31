package com.java.PTPMHDV13.Vinfast_Sales.exception;

import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class,
            MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            name = "Handle exception when the data invalid. (@RequestBody, @RequestParam, @PathVariable)",
                            summary = "Handle Bad Request",
                            value = """
                                    {
                                         "timestamp": "2024-10-29T06:07:35.321+00:00",
                                         "status": 400,
                                         "path": "/api/v1/...",
                                         "error": "Invalid Payload",
                                         "message": "{data} must be not blank"
                                     }
                                    """
                    ))}
    )
    public ResponseEntity<ApiErrorResponse> handleValidationException(Exception e, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

        String message = e.getMessage();
        if (e instanceof MethodArgumentNotValidException) {
            int start = message.lastIndexOf("[") + 1;
            int end = message.lastIndexOf("]") - 1;
            message = message.substring(start, end);
            errorResponse.setMessage(message);
        } else if (e instanceof MissingServletRequestParameterException) {
            errorResponse.setMessage(message);
        } else errorResponse.setMessage("Invalid Data");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AlReadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "409", description = "Conflict",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            name = "409 Response",
                            summary = "Handle exception when input data is conflicted",
                            value = """
                                    {
                                      "timestamp": "2024-10-29T06:07:35.321+00:00",
                                      "status": 409,
                                      "path": "/api/v1/...",
                                      "error": "Conflict",
                                      "message": "{data} exists, Please try again!"
                                    }
                                    """
                    ))})
    public ResponseEntity<ApiErrorResponse> handleAlReadyExistException(Exception e, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Bad Request",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            name = "404 Response",
                            summary = "Handle exception when resource not found",
                            value = """
                                    {
                                      "timestamp": "2024-10-29T06:07:35.321+00:00",
                                      "status": 404,
                                      "path": "/api/v1/...",
                                      "error": "Not Found",
                                      "message": "{data} not found"
                                    }
                                    """
                    ))})
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(Exception e, WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setTimestamp(new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationServiceException.class, SignatureException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ApiResponse(responseCode = "403", description = "Forbidden",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            name = "403 Response",
                            summary = "Handle exception when Access denied",
                            value = """
                                    {
                                      "timestamp": "2024-10-29T06:07:35.321+00:00",
                                      "status": 403,
                                      "path": "/api/v1/...",
                                      "error": "Access denied",
                                      "message": "cannot access {data}",
                                    }
                                    """
                    ))})
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(WebRequest request) {
        ApiErrorResponse errorResponse = new ApiErrorResponse();
        errorResponse.setMessage("Access denied");
        errorResponse.setTimestamp(new Date());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                    examples = @ExampleObject(
                            name = "500 Response",
                            summary = "Handle exception when internal server error",
                            value = """
                                            {
                                              "timestamp": "2024-10-29T06:07:35.321+00:00",
                                              "status": 500,
                                              "path": "/api/v1/...",
                                              "error": "Internal Server Error",
                                              "message": "Connection timeout, please try again"
                                            }
                                            """
                    ))})
    ResponseEntity<ApiErrorResponse> handlingRuntimeException(Exception exception, WebRequest request) {
        log.error("Exception: ", exception);
        ApiErrorResponse apiResponse = new ApiErrorResponse();
        apiResponse.setTimestamp(new Date());
        apiResponse.setPath(request.getDescription(false).replace("uri=", ""));
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
