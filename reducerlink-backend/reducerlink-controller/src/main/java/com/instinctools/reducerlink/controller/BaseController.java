package com.instinctools.reducerlink.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.instinctools.reducerlink.service.support.AppException;
import com.instinctools.reducerlink.service.support.AppResponse;
import com.instinctools.reducerlink.service.support.PagedResult;
import com.instinctools.reducerlink.service.support.TransformUtils;
import com.instinctools.reducerlink.service.support.ValidationResult;

public class BaseController {
    public static final String ERROR_SERVER = "server";
    public static final String ERROR_UPLOAD = "upload";
    public static final String ERROR_VALIDATION = "validation";

    protected static final int MAX_PAGE_SIZE = 128;

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private MessageSource messageSource;

    protected <S, D> List<D> mapList(Collection<S> collection, Function<S, D> mapper) {
        return TransformUtils.mapList(collection, mapper);
    }

    protected Map<String, Object> toMap(Object... args) {
        return TransformUtils.toMap(args);
    }

    protected HttpHeaders buildDefaultResponseHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    protected ResponseEntity<AppResponse> buildOk(Object data) {
        return new ResponseEntity<AppResponse>(
            new AppResponse(AppResponse.STATUS_OK, null, data),
            buildDefaultResponseHeaders(),
            HttpStatus.OK
        );
    }

    protected ResponseEntity<AppResponse> buildError(String errorCode) {
        return buildError(errorCode, null);
    }

    protected ResponseEntity<AppResponse> buildError(String errorCode, Object data) {
        return new ResponseEntity<AppResponse>(
            new AppResponse(AppResponse.STATUS_ERROR, errorCode, data),
            buildDefaultResponseHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    protected <T> ResponseEntity<AppResponse> buildValidationResult(ValidationResult<T> result) {
        return buildValidationResult(result, null);
    }

    protected <T> ResponseEntity<AppResponse> buildValidationResult(ValidationResult<T> result, Function<T, ?> mapper) {
        if (!result.isFaulted()) {
            return buildOk(mapper == null ? result.getResult() : mapper.apply(result.getResult()));
        } else {
            return buildError(result.getErrorCode() == null ? ERROR_VALIDATION : result.getErrorCode(), result.getErrorList());
        }
    }

    protected <T> ResponseEntity<AppResponse> buildPagedResult(PagedResult<T> result, Function<T, ?> mapper) {
        return buildOk(toMap(
            "totalPages", result.getTotalPages(),
            "list", mapList(result.getList(), mapper)
        ));
    }

    protected String tran(String code) {
        return tran(code, null);
    }

    protected String tran(String code, Object[] args) {
        return messageSource.getMessage(code, args, code, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(value = AppException.class)
    protected void handleAppError(HttpServletRequest request, HttpServletResponse response, AppException e) {
        sendRawResponse(response, new AppResponse(
            AppResponse.STATUS_ERROR,
            e.getErrorCode(),
            e.getData()
        ));
    }

    @ExceptionHandler(value = Exception.class)
    protected void handleGeneralException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("Unhandled exception occured at URL \"" + request.getRequestURL() + "\"", e);
        String message;

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        message = sw.getBuffer().toString();

        sendRawResponse(response, new AppResponse(AppResponse.STATUS_ERROR, ERROR_SERVER, message));
    }

    protected void sendRawResponse(HttpServletResponse response, AppResponse appResponse) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        try {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            // text/html instead of application/json for IE
            jsonConverter.write(appResponse, MediaType.TEXT_HTML, new ServletServerHttpResponse(response));
            response.flushBuffer();
        } catch (Exception inner) {
            throw new RuntimeException(inner);
        }
    }

    protected long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }
}