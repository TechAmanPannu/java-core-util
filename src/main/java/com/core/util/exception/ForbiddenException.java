package com.core.util.exception;


import com.core.util.exception.enums.ErrorCode;


/**
 * @author Amandeep Singh
 */

public class ForbiddenException extends AbstractRuntimeException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(ErrorCode error, String msg) {
        super(error, msg);
    }

    public ForbiddenException(ErrorCode error, String msg, Object info) {
        super(error, msg, info);
    }
}
