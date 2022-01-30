package com.core.util.http;

import com.core.util.exception.JavaCoreException;
import junit.framework.TestCase;
import org.junit.Test;

public class CoreHttpClientTest {

    @Test
    public void test() {

        HttpResponse response = null;
        try {
            response = CoreHttpClient.makeGetRequest("https://my.staging.shotli.app/workspace/WV3Bk/folder/FOV3Bl", null, HttpResponse.class);
        } catch (JavaCoreException e) {
            e.printStackTrace();
        }
    }

}