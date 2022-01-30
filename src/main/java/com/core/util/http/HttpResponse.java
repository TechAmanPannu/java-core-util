package com.core.util.http;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Amandeep Singh
 */

@Data
public class HttpResponse implements Serializable {

    protected int statusCode;

    protected String responseContent;

    protected Map<String, List<String>> headers;

    protected long responseTimeMillis;

    protected boolean wasSuccessful(){
        return (statusCode >= 200 && statusCode < 299);
    }

}
