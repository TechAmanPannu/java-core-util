package com.core.util.utilities;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.util.*;

/**
 * @author Amandeep Singh
 */

@Slf4j
public class ObjUtil {
    private static ObjectMapper jacksonMapper;

    static {
        jacksonMapper = new ObjectMapper();
        JacksonMapper.setConfig(jacksonMapper);
    }

    public static ObjectMapper getJacksonMapper(){
        return jacksonMapper;
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null || value.length() <= 0);
    }

    public static boolean isBlank(String value) {
        return (value == null || value.trim().length() <= 0);
    }

    public static boolean isNullOrEmpty(Collection<?> obj) {
        return (obj == null || obj.isEmpty());
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }


    public static boolean isRequestNullOrEmpty(String... requestParams) {

        boolean resp = false;
        for (int i = 0; i < requestParams.length; i++) {
            if (requestParams[i] == null || requestParams[i] == "" || requestParams[i].trim() == "") {
                resp = true;
                break;
            }
        }
        return resp;
    }

    public static String getJson(Object object) {

        if(object == null)
            return null;

        try {
            return jacksonMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static byte[] getJsonAsBytes(Object object) {

        try {
            return jacksonMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Map<String, Object> getMapFromJson(String json) {

        try {
            return jacksonMapper.readValue(json,
                    new TypeReference<HashMap<String, Object>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
    public static Set<String> getSetFromJson(String json){

        try {
            return jacksonMapper.readValue(json,
                    new TypeReference<HashSet<String>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static  List<Object> getListFromJson(String json){

        try {
            return jacksonMapper.readValue(json,
                    new TypeReference<ArrayList<Object>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static  List<String> getStringListFromJson(String json){

        try {
            return jacksonMapper.readValue(json,
                    new TypeReference<ArrayList<String>>() {
                    });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T safeConvertJson(String json, Class<T> clazz) {
        try {
            return jacksonMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T safeConvertMap(Map<String, Object> map, Class<T> clazz) {
        try {
            return jacksonMapper.convertValue(map, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Map<String, Object> safeDecodeMap(String encodedString){

        if(ObjUtil.isBlank(encodedString))
            return null;
        try {
            return getMapFromJson(new String(Base64.getDecoder().decode(encodedString.getBytes())));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Map<String, Object> convertToMap(Object data) {

        try {
            if (data == null)
                return null;
            return jacksonMapper.convertValue(data, new TypeReference<Map<String, Object>>() {
            });
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String toFormParams(Object obj) {

        Map map = jacksonMapper.convertValue(obj, Map.class);
        return mapToUtf8FormParam(map);
    }

    /**
     * Map to form param.
     *
     * @param map the map
     * @return the string
     */
    public static String mapToUtf8FormParam(Map<String, Object> map) {

        if (map == null)
            return null;

        StringBuilder builder = new StringBuilder();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                if (builder.length() > 0) {
                    builder.append("&");
                }

                Object value = entry.getValue();
                if (value instanceof String) {
                    value = URLEncoder.encode((String) value, "UTF-8");
                }

                builder.append(String.format("%s=%s", entry.getKey(), value));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int safeParseInt(String numStr) {
        try {
            return Integer.parseInt(numStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static <T> Set<T> getHashSet(List<T> listItem) {

        return listItem != null ? new HashSet<>(listItem) : new HashSet<>();
    }

}
