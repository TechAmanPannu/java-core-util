package com.core.util.exception;


import com.core.util.exception.enums.ErrorCode;

/**
 * @author Amandeep Singh
 */

public class NotFoundException extends AbstractRuntimeException {


    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(ErrorCode error) {
        super(error);
    }

    public NotFoundException(ErrorCode error, String msg) {
        super(error, msg);
    }

    public NotFoundException(ErrorCode error, String msg, Object info) {
        super(error, msg, info);
    }
}
