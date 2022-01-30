package com.core.util.http;

import com.core.util.exception.JavaCoreException;
import com.core.util.http.retry.RetryPolicy;
import com.core.util.utilities.QueryParams;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.MalformedURLException;
import java.util.Map;


/**
 * @author Amandeep Singh
 */


public final class CoreHttpClient {

    private CoreHttpClient() {

    }

    public static <T> T makeRequest(ApiRequest<T> request) throws JavaCoreException {
        return URLFetcher.makeRequest(request);
    }

    public static <T> T makeRequest(HttpMethod method, String url, Object payload, Class<T> responseType) throws JavaCoreException {
        return makeRequest(method, url, null, payload, null, responseType);
    }

    public static <T> T makeRequest(HttpMethod method, String url, QueryParams queryParams, Object payload, Map<String, String> headers, Class<T> responseType) throws JavaCoreException {
        ApiRequest<T> request = constructRequest(method, url, queryParams, payload, headers, responseType);
        return makeRequest(request);
    }

    public static <T> T makeRequest(HttpMethod method, String url, Object payload, Class<T> responseType, RetryPolicy retryPolicy) throws JavaCoreException {
        return makeRequest(method, url, null, payload, null, responseType, retryPolicy);
    }

    public static <T> T makeRequest(HttpMethod method, String url, QueryParams queryParams, Object payload, Map<String, String> headers, Class<T> responseType, RetryPolicy retryPolicy) throws JavaCoreException {
        if (retryPolicy == null) {
            retryPolicy = RetryPolicy.retryCount();
        }
        ApiRequest<T> request = constructRequest(method, url, queryParams, payload, headers, responseType);
        return retryPolicy.retry(() -> makeRequest(request));
    }


    public static <T> T makeGetRequest(String url, QueryParams queryParams, Class<T> responseType) throws JavaCoreException {
        return makeRequest(HttpMethod.GET, url, queryParams, null, null, responseType);
    }

    public static <T> T makeGetRequest(String url, QueryParams queryParams, Class<T> responseType, RetryPolicy retryPolicy) throws JavaCoreException {
        return makeRequest(HttpMethod.GET, url, queryParams, null, null, responseType, retryPolicy);
    }


    private static  <T> ApiRequest<T> constructRequest(HttpMethod method, String url, QueryParams queryParams, Object payload, Map<String, String> headers, Class<T> responseType) throws JavaCoreException {
        if (queryParams != null)
            url += "?" + queryParams.toQueryString();
        ApiRequest<T> request = null;
        try {
            request = new ApiRequest<T>(method, url, responseType)
                    .respContentType(MediaType.JSON)
                    .jsonPayload(payload);
        } catch (JsonProcessingException | MalformedURLException e) {
            throw new JavaCoreException(e.getMessage(), e);
        }
        if (headers != null)
            request.addAllHeaders(headers);
        return request;
    }
}
