package com.thiagodd.stackoverclone.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.thiagodd.stackoverclone.domain.exception.EntityInUseException;
import com.thiagodd.stackoverclone.domain.exception.EntityNotFoundException;
import com.thiagodd.stackoverclone.domain.exception.ViolationBusinessRulesException;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DETAIL_INVALID_FORMAT = "The property '%s' was assigned the value '%s', which is of an invalid type. " +
            "Correct and enter a value compatible with the type '%s'";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
        ErrorResponse body = createResponseError(status, problemType).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<Object> handleEntityInUseException(EntityInUseException exception, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        ErrorResponse body = createResponseError(status, problemType).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTITY_IN_USE;
        ErrorResponse body = createResponseError(status, problemType).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ViolationBusinessRulesException.class)
    public ResponseEntity<Object> handleBusinessException(ViolationBusinessRulesException exception, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERROR_BUSINESS;
        ErrorResponse body = createResponseError(status, problemType).build();

        return this.handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        ErrorResponse body = createResponseError(HttpStatus.NOT_FOUND, problemType)
                .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<Field> errosFields = bindingResult.getFieldErrors()
                .stream().map(
                        fieldError -> Field
                                .builder()
                                .name(fieldError.getField())
                                .reason(fieldError.getDefaultMessage())
                                .build())
                .toList();

        ProblemType problemType = ProblemType.PARAMETER_NOT_VALID;

        ErrorResponse body = createResponseError(HttpStatus.BAD_REQUEST, problemType)
                .fields(errosFields)
                .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.METHOD_NOT_SUPPORTED;

        ErrorResponse body = createResponseError(HttpStatus.METHOD_NOT_ALLOWED, problemType)
                .build();

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            // TODO implements pending
        }

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        ErrorResponse body = createResponseError(HttpStatus.valueOf(status.value()), problemType).build();

        return this.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
        String detail = String.format(DETAIL_INVALID_FORMAT, path, ex.getValue(), ex.getTargetType().getSimpleName());

        ErrorResponse body = createResponseError(HttpStatus.valueOf(status.value()), problemType)
                .detail(detail)
                .build();


        return this.handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             @Nullable Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
        if (body == null) {
            body = ErrorResponse
                    .builder()
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .status(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body = ErrorResponse
                    .builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private ErrorResponse.ErrorResponseBuilder createResponseError(HttpStatus status, ProblemType type) {
        return ErrorResponse
                .builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .timestamp(Instant.now())
                .detail(type.getDetail());
    }
}
