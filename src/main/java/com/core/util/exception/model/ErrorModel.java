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

    private String error;

    public ErrorModel(String error) {
        this.error = error;
    }

}
