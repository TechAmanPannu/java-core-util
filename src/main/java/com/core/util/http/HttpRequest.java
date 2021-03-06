package com.core.util.http;

import lombok.Data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amandeep Singh
 */

@Data
public class HttpRequest {

    private URL url;

    private byte[] payload;

    private String jsonPayload;

    private HttpMethod method;

    private Map<String, String> headers;

    private int connectionTimeOut;

    protected MediaType contentType;

    /**
     * Instantiates a new Http request.
     *
     * @param url the url
     * @param method the method
     * @throws MalformedURLException the malformed uRL exception
     */
    public HttpRequest(String url, HttpMethod method) throws MalformedURLException {
        this.setUrl(url);
        this.method = method;
    }


    /**
     * Sets url.
     *
     * @param url the url
     * @throws MalformedURLException the malformed uRL exception
     */
    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    /**
     * Set url.
     *
     * @param url the url
     */
    public void setUrl(URL url) {
        this.url = url;
    }


    public void setPayload(String payload) {
        this.setPayload(payload == null ? null : payload.getBytes());
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }


    /**
     * Add header.
     *
     * @param key the key
     * @param value the value
     */
    public void addHeader(String key, String value) {

        if (this.headers == null)
            this.headers = new HashMap<>();

        this.headers.put(key, value);
    }

    /**
     * Get header value.
     *
     * @param key the key
     * @return the string
     */
    public String getHeaderValue(String key) {

        return this.headers == null ? null : this.headers.get(key);
    }

    /**
     * Add All headers.
     *
     * @param headers Map</String,String> contains header name and its value
     */
    public void addAllHeaders(Map<String, String> headers) {

        if (this.headers == null)
            this.headers = new HashMap<>();

        if (headers != null)
            this.headers.putAll(headers);
    }
}
