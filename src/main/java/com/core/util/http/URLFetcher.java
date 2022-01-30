package com.core.util.http;

import com.core.util.exception.JavaCoreException;
import com.core.util.exception.model.ErrorModel;
import com.core.util.utilities.JacksonMapper;
import com.core.util.utilities.ObjUtil;

import java.io.*;
import java.net.HttpURLConnection;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * @author Amandeep Singh
 */


public class URLFetcher {

    private static final JacksonMapper objMapper = new JacksonMapper();

    public static <T> T makeRequest(ApiRequest<T> request) throws JavaCoreException {
        try {
            HttpURLConnection conn = (HttpURLConnection) request.getUrl().openConnection();
            if (request.getMethod().toString().equals("PATCH")) { allowMethods("PATCH"); }
            conn.setRequestMethod(request.getMethod().toString());
            int timeout = request.getConnectionTimeOut() > 0 ? request.getConnectionTimeOut() : 30;
            conn.setConnectTimeout(timeout * 1000);
            if (request.getHeaders() != null) {
                for (String key : request.getHeaders().keySet()) {
                    conn.setRequestProperty(key, request.getHeaderValue(key));
                }
            }

            long requestStartTimeMillis = System.currentTimeMillis();
            if (request.getMethod() != HttpMethod.GET) {
                conn.setRequestProperty("Content-Type", request.getContentType().getContentType());
                if (request.getPayload() != null) {
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    os.write(request.getPayload());
                    os.flush();
                }
            }

            InputStream stream = getInputStream(conn);
            long requestEndTimeMillis = System.currentTimeMillis();
            long responseTimeMillis = requestEndTimeMillis - requestStartTimeMillis;
            String respData = streamToString(stream);
            if (respData == null || respData.isEmpty())
                throw new JavaCoreException("Unable to parse the response body");
            int responseCode = conn.getResponseCode();
            if (responseCode < 200 || responseCode > 399) {
                ErrorModel error = new ErrorModel(respData);
                String msg = "";
                if (error != null && !ObjUtil.isBlank(error.getError())) {
                    msg = error.getError();
                    System.out.println(msg);
                }
                msg = "Status Code " + responseCode + " - " + (msg);
                JavaCoreException e = new JavaCoreException(msg, responseCode, responseTimeMillis).apiResponse(error);
                e.setStatusCode(responseCode);
                throw e;
            }
            if(request.getResponseType() == HttpResponse.class) {
                HttpResponse httpResponse = new HttpResponse();
                httpResponse.setStatusCode(responseCode);
                httpResponse.setResponseContent(respData);
                httpResponse.setResponseTimeMillis(responseTimeMillis);
               httpResponse.setHeaders(conn.getHeaderFields());
                return (T) httpResponse;
            }
            if(request.getResponseType() == String.class) { return (T) respData; }
            return ObjUtil.safeConvertJson(respData, request.getResponseType());
        } catch (IOException e) {
            throw new JavaCoreException(e.getMessage(), e);
        }
    }

    public static String streamToString(InputStream is) throws IOException {
        if (is == null)
            return null;

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        return sb.toString();
    }

    public static InputStream getInputStream(HttpURLConnection conn) throws IOException {

        InputStream stream = null;
        try {
            stream = conn.getInputStream();
        } catch (IOException e) {
            if (conn.getResponseCode() != 200) {
                stream = conn.getErrorStream();
            }
        }
        return stream;
    }

    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}

