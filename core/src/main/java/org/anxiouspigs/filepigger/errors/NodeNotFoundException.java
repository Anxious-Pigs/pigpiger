package org.anxiouspigs.filepigger.errors;

/**
 * Indicates that the desired node was not found.
 */
public class NodeNotFoundException extends FilepiggerException {

    public NodeNotFoundException(String message) {
        super(message);
    }

    public NodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
