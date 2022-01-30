package com.core.util.http.retry;

import com.core.util.exception.JavaCoreException;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Amandeep Singh
 */

public class RetryPolicy implements Serializable {

    private static final long serialVersionUID = -6666665939530372350L;

    private final int retryCount;
    private int backOffInitialDelayMillis;
    private int maxBackOffDelayMillis;

    private RetryPolicy(final int retryCount) {
        this.retryCount = retryCount;
        this.backOffInitialDelayMillis = 1000;
        this.maxBackOffDelayMillis = 1024000;
    }

    public <T> T retry(RetryExecutor<T> retryExecutor) throws JavaCoreException {
        if(retryExecutor == null) { return null; }
        int attempt = 0;
        T response = null;
        boolean tryAgain = false;
        do {
            attempt++;
            System.out.println("Attempt : " + attempt);
        try {
            response =  retryExecutor.execute();
        } catch (JavaCoreException e) {
           if(!e.isServerError()) { throw e; }
            tryAgain =  attempt <= retryCount;
        }
            if (tryAgain) {
                int sleepTime = backOffInitialDelayMillis / 2 + new Random().nextInt(backOffInitialDelayMillis);
                sleep(sleepTime);
                if (2 * backOffInitialDelayMillis < maxBackOffDelayMillis) {
                    backOffInitialDelayMillis *= 2;
                }
            }
        } while (tryAgain);
        if(response == null) { throw new JavaCoreException(500, "something went wrong"); }
        return response;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static RetryPolicy retryCount(Integer retryCount) {
        return new RetryPolicy(retryCount);
    }

    public static RetryPolicy retryCount() {
        return new RetryPolicy(1);
    }

}
