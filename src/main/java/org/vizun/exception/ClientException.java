package org.vizun.exception;

public class ClientException extends Exception {
    
    public ClientException(String exception) {
        super(exception);
    }
    
    public ClientException(Throwable throwable) {
        super(throwable);
    }
    
    public ClientException(String exception, Throwable throwable) {
        super(exception, throwable);
    }
    
    public ClientException() {
        super();
    }
}
