package com.springbootneo4j.exceptions;


/**
 * A runtime exception indicating a tenant requested by a client was not found.
 */
public class RequestValidationException extends RuntimeException {

    private static final long serialVersionUID = -896015072208863L;

    public RequestValidationException(String message) {
        super(message);
    }
}
