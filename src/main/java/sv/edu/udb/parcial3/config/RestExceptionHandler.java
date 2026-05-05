package sv.edu.udb.parcial3.config;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;
import sv.edu.udb.parcial3.config.web.ApiError;
import sv.edu.udb.parcial3.config.web.ApiErrorWraper;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatusCode status,
                                                                  final WebRequest request) {
        final ApiErrorWraper errorWraper = procesarErrores(ex.getBindingResult().getAllErrors());

        return handleExceptionInternal(ex, errorWraper, headers, status, request);

    }

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity<Object> handleDataAccesException(DataAccessException ex, WebRequest request){
        return handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSqlException(SQLException ex, WebRequest request){
        return handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(MethodNotAllowedException.class)
    protected ResponseEntity<Object> handleMethodNotAllowedExceptionException(MethodNotAllowedException ex, WebRequest request){
        return handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             HttpHeaders headers,
                                                             HttpStatusCode status,
                                                             WebRequest request) {
        return handleExceptionInternal(ex, null, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             @Nullable Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode status,
                                                             WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        if (Objects.isNull(body)) {
            ApiErrorWraper errorWraper = mensajeApiErrorWraper((HttpStatus) status, ex);
            return new ResponseEntity<>(errorWraper, headers, status);
        }

        return new ResponseEntity<>(body, headers, status);
    }

    //Utilidades

    private ApiErrorWraper procesarErrores(final List<ObjectError> errores) {
        ApiErrorWraper errorWraper = new ApiErrorWraper();

        errores.forEach(e -> {
            if (e instanceof FieldError) {
                FieldError fieldError = (FieldError) e;
                errorWraper.addFieldError(fieldError.getClass().getSimpleName(), "Atributo invalido", fieldError.getField(), fieldError.getDefaultMessage());
            } else {
                errorWraper.addFieldError(e.getClass().getSimpleName(), "Atributo invalido", "base", e.getDefaultMessage());
            }
        });
        return errorWraper;
    }

    private ApiError crearApiError(final HttpStatus status, final Exception ex) {
        final String tipoExcepcion = ex.getClass().getSimpleName();
        final String descripcion = StringUtils.defaultIfBlank(ex.getMessage(), ex.getClass().getSimpleName());
        String fuente = "base";
        if (ex instanceof MissingServletRequestParameterException) {
            final MissingServletRequestParameterException parameterException = (MissingServletRequestParameterException) ex;
            fuente = parameterException.getParameterName();
        } else if (ex instanceof MissingPathVariableException) {
            final MissingPathVariableException pathVariableException = (MissingPathVariableException) ex;
            fuente = pathVariableException.getVariableName();
        }

        return ApiError.builder()
                .titulo(status.getReasonPhrase())
                .tipo(tipoExcepcion)
                .descripcion(descripcion)
                .fuente(fuente)
                .estatus(status.value())
                .build();


    }

    private ApiErrorWraper mensajeApiErrorWraper(HttpStatus status, Exception ex) {
        return mensajeApiErrorWraper(crearApiError(status, ex));
    }

    private ApiErrorWraper mensajeApiErrorWraper(final ApiError error) {
        ApiErrorWraper errorWraper = new ApiErrorWraper();
        errorWraper.addApiError(error);
        return errorWraper;
    }
}
