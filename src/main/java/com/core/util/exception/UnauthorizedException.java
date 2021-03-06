package com.core.util.exception;


import com.core.util.exception.enums.ErrorCode;

/**
 * @author Amandeep Singh
 */

public class UnauthorizedException extends AbstractRuntimeException{

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(ErrorCode error) {
        super(error);
    }

    public UnauthorizedException(ErrorCode error, String msg) {
        super(error, msg);
    }

    public UnauthorizedException(ErrorCode error, String msg, Object info) {
        super(error, msg, info);
    }
}
