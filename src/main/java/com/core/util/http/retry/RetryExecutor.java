package com.core.util.http.retry;

import com.core.util.exception.JavaCoreException;

/**
 * @author Amandeep Singh
 */

@FunctionalInterface
public interface RetryExecutor<T> {

     T execute() throws JavaCoreException;
}

