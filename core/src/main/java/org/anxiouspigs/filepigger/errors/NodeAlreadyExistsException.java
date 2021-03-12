package org.anxiouspigs.filepigger.errors;

/**
 * Indicates that the node to be created already exists.
 */
public class NodeAlreadyExistsException extends FilepiggerException {

    public NodeAlreadyExistsException(String message) {
        super(message);
    }

    public NodeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
