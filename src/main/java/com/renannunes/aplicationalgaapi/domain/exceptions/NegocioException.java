package com.renannunes.aplicationalgaapi.domain.exceptions;

public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NegocioException(String mesage) {
        super(mesage);
    }
}
