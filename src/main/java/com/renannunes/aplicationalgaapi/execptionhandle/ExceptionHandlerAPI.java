package com.renannunes.aplicationalgaapi.execptionhandle;

import com.renannunes.aplicationalgaapi.domain.exceptions.SalvaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerAPI extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SalvaException.class)
    public ResponseEntity<?> handleSave(SalvaException save, WebRequest webRequest) {
        var erro = new CustomError();
        erro.setTitle(save.getMessage());
        erro.setTimestamp(new Date().getTime());
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setDeveloperMessage(save.getClass().getName());
        erro.setDetails(save.getMessage());
        return handleExceptionInternal(save, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var erro = new CustomError();
        var erroCampos = new ArrayList<CustomError.Campo>();

        for (ObjectError erros : ex.getBindingResult().getAllErrors()) {
            String name = erros.getObjectName();
            String message = erros.getDefaultMessage();
            erroCampos.add(new CustomError.Campo(name, message));
        }
        erro.setStatus(status.value());
        erro.setTimestamp(new Date().getTime());
        erro.setTitle("Field validation error");
        erro.setDeveloperMessage(ex.getClass().getName());
        erro.setCampos(erroCampos);
        return super.handleExceptionInternal(ex, erro, headers, status, request);
    }


}
