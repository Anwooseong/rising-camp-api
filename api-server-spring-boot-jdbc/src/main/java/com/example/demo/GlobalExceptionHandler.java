package com.example.demo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handlerBaseException(EmptyResultDataAccessException e) {
        System.out.println(e.getClass());
        return new BaseResponse<>(new BaseException(BaseResponseStatus.EMPTY_RESULT).getStatus());
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handlerBaseException(BadSqlGrammarException e) {
        System.out.println(e.getClass());
        return new BaseResponse<>(new BaseException(BaseResponseStatus.BAD_SQL).getStatus());
    }

    @ExceptionHandler(UncategorizedSQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handlerBaseException(UncategorizedSQLException e) {
        System.out.println(e.getClass());
        return new BaseResponse<>(new BaseException(BaseResponseStatus.SQL_PARAMETERS).getStatus());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse userException(BaseException baseException) {
        return new BaseResponse<>(baseException.getStatus());
    }
}
