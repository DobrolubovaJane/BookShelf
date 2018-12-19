package com.bookshelf.exception;

import com.bookshelf.service.impl.BookServiceImpl;
import com.bookshelf.service.impl.ReaderServiceImpl;
import io.swagger.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice()
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException ex) {
        return getError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorModel> handleBadRequestException(BadRequestException ex) {
        return getError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorModel> getError(String message, HttpStatus status) {
        ErrorModel error = new ErrorModel();
        error.setMessage(message);
        return new ResponseEntity<>(error, status);
    }
}