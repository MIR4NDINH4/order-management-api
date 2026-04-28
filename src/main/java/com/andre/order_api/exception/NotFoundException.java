package com.andre.order_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    // Construtor para apenas a mensagem
    public NotFoundException(String mensagem) {
        super(mensagem); // Passa a mensagem para a classe Exception pai
    }

    // Construtor para mensagem e causa (boa prática para encadeamento)
    public NotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
