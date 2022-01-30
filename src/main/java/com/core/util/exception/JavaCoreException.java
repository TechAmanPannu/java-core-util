package com.core.util.exception;

import com.core.util.exception.model.ErrorModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Amandeep Singh
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class JavaCoreException extends Exception {

    private ErrorModel error;

    private int statusCode;

    private boolean isServerError;


    public JavaCoreException(String message) {
        super(message);
    }

    public JavaCoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public JavaCoreException(int statusCode, String message) {
        super(message);
        setStatusCode(statusCode);
    }

    public JavaCoreException apiResponse(ErrorModel error) {
        this.error = error;
        return this;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.isServerError = (this.statusCode >= 500 && this.statusCode < 600);
    }
}

