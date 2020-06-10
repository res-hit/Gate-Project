package com.RestClientPalocs.Utils.Exceptions;

public class MissingMandatoryParameterException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MissingMandatoryParameterException(){}
    public MissingMandatoryParameterException(String msg) { super(msg); }
}
