package org.anxiouspigs.filepigger.errors;

/**
 * The base class of all other Filepigger exceptions
 */
public class FilepiggerException extends RuntimeException{

    private final static long serialVersionUID = 1L;

    public FilepiggerException() {
        super();
    }

    public FilepiggerException(String message) {
        super(message);
    }

    public FilepiggerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilepiggerException(Throwable cause) {
        super(cause);
    }

}
