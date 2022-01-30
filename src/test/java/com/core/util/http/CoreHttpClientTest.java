package com.core.util.http;

import com.core.util.exception.JavaCoreException;
import com.core.util.utilities.ObjUtil;
import com.core.util.utilities.Preconditions;
import junit.framework.TestCase;
import org.junit.Test;

public class CoreHttpClientTest {

    @Test
    public void test() {

        HttpResponse response = null;
        try {
            response = CoreHttpClient.makeGetRequest("https://amazon.in", null, HttpResponse.class);
        } catch (JavaCoreException e) {
            e.printStackTrace();
        }
        Preconditions.checkArgument(ObjUtil.isBlank(null), "Invalid value");
    }

}