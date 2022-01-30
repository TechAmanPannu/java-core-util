package com.core.util.http;

/**
 * @author Amandeep Singh
 */

public enum MediaType {

    JSON("application/json");

    private final String contentType;

    MediaType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
