package com.core.util.exception.model;

import com.core.util.exception.enums.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Amandeep Singh
 */

@Data
@NoArgsConstructor
public class ErrorModel implements Serializable {

    private String code;
    private String message;

    public ErrorModel(ErrorCode errorCode, String message) {
        this.code = errorCode != null ? errorCode.toString() : null;
        this.message = message;
    }

    public ErrorModel(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

}
