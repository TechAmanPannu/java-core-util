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
public class CoreException extends Exception {

    private ErrorModel error;

    private int statusCode;

    private boolean isServerError;

    private long responseTimeMillis;

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, int statusCode, long responseTimeMillis) {
        super(message);
        this.statusCode = statusCode;
        this.responseTimeMillis = responseTimeMillis;
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(int statusCode, String message) {
        super(message);
        setStatusCode(statusCode);
    }

    public CoreException apiResponse(ErrorModel error) {
        this.error = error;
        return this;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        this.isServerError = (this.statusCode >= 500 && this.statusCode < 600);
    }
}

