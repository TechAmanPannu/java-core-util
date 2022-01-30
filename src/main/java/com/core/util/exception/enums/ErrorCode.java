package com.core.util.exception.enums;

/**
 * @author Amandeep Singh
 */

import com.fasterxml.jackson.annotation.JsonValue;

public interface ErrorCode {

	@JsonValue
    String getErrorCode();

	String toString();
}
