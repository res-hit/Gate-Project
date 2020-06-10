package com.RestClientPalocs.Utils.Exceptions;

public class ServerErrorException extends Exception  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServerErrorException(){}
    public ServerErrorException(String msg) { super(msg); }
}
