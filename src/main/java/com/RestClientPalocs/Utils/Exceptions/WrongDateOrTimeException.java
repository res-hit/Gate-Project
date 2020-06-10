package com.RestClientPalocs.Utils.Exceptions;

public class WrongDateOrTimeException extends Exception {

    private static final long serialVersionUID = 1L;
    public WrongDateOrTimeException(){}
    public WrongDateOrTimeException(String msg) { super(msg); }
}
