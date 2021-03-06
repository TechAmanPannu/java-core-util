package com.core.util.exception;


import com.core.util.exception.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Amandeep Singh
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractException extends Exception {

    private ErrorCode error;

    private Object info;

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractException(Throwable cause) {
        super(cause);
    }

    public AbstractException(ErrorCode error){
        this(error, null);
    }

    public AbstractException(ErrorCode error, String msg){
        this(error,msg,null);
    }

    public AbstractException(ErrorCode error, String msg, Object info){
        super(msg);
        this.error = error;
        this.info = info;
    }

    public boolean hasError() {
        return error != null;
    }

    public String errorCode(){
        return error == null ? null : error.getErrorCode();
    }
}
