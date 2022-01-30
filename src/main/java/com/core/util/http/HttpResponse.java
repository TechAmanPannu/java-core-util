package com.core.util.http;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Amandeep Singh
 */

@Data
public class HttpResponse {

    private int statusCode;

    private String responseContent;

    private Map<String, List<String>> headers;

    public boolean wasSuccessful(){
        return (statusCode >= 200 && statusCode < 299);
    }

}
