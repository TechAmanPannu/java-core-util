package com.core.util.exception;


import com.core.util.exception.enums.ErrorCode;

/**
 * @author Amandeep Singh
 */

public class EntityException extends AbstractRuntimeException {

    private static final long serialVersionUID = -5638893712546454536L;

    public EntityException() {
        super();
    }

    public EntityException(ErrorCode error) {
        super(error);
    }

    public EntityException(ErrorCode error, String message, Object info) {
        super(error, message, info);
    }

    public EntityException(ErrorCode error, String message) {
        super(error, message);
    }
}
