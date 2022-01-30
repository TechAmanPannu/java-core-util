package com.core.util.utilities;

import com.core.util.exception.IllegalArgException;
import com.core.util.exception.enums.ErrorCode;


public final class Preconditions {

    private Preconditions(){}

    public static void checkArgument(boolean expression) {

        if (expression)
            throw new IllegalArgException();
    }

    public static void checkArgument(boolean expression, String message) {
        checkArgument(expression, null, message, null);
    }

    public static void checkArgument(boolean expression, String message, String field) {
        checkArgument(expression, null, message, field);
    }

    public static void checkArgument(boolean expression, ErrorCode errorCode, String message) {
        checkArgument(expression, errorCode, message, null);
    }

    public static void checkArgument(boolean expression, ErrorCode errorCode, String message, Object info) {

        if (expression)
            throw new IllegalArgException(errorCode, String.valueOf(message), info);
    }

}
