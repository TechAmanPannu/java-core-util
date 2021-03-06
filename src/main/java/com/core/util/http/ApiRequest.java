package com.core.util.http;

import com.core.util.utilities.ObjUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.MalformedURLException;


@Data
@EqualsAndHashCode(callSuper = false)
public class ApiRequest<T> extends HttpRequest {

    private final Class<T> responseType;

    public ApiRequest(HttpMethod method, String url, Class<T> responseType) throws MalformedURLException {
        super(url, method);
        this.responseType = responseType;
    }

    public ApiRequest<T> respContentType(MediaType type) {
        if (type != null)
            this.contentType = type;
        return this;
    }

    public ApiRequest<T> jsonPayload(Object payload) throws JsonProcessingException {

        if (payload != null)
            this.setPayload(ObjUtil.getJson(payload));
        return this;
    }
}