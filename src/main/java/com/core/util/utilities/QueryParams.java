package com.core.util.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public final class QueryParams {

    final Map<Object, Object> queryParams;

    public QueryParams() {
        queryParams = new HashMap<>();
    }

    public QueryParams(Map<Object, Object> queryParams) {
        this.queryParams = queryParams == null ? new HashMap<>() : queryParams;
    }

    public static QueryParams builder() {
        return new QueryParams();
    }

    public static QueryParams builder(Map<Object, Object> queryParams) {
        return new QueryParams(queryParams);
    }

    public QueryParams add(Object key, Object value) {
        queryParams.put(key, value);
        return this;
    }

    public QueryParams addNonNull(String key, String value) {

        if (key != null && value != null)
            add(urlEncodeUTF8(key), urlEncodeUTF8(value));
        return this;
    }

    public QueryParams addNonNull(Object key, Object value) {

        if (key != null && value != null)
            add(key, value);
        return this;
    }

    public Map<Object, Object> toMap() {
        return queryParams;
    }

    public String toQueryString() {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : queryParams.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }

    public String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
