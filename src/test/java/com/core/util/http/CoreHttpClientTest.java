package com.core.util.http;

import com.core.util.exception.CoreException;
import com.core.util.utilities.ObjUtil;
import com.core.util.utilities.Preconditions;
import org.junit.Test;

public class CoreHttpClientTest {

    @Test
    public void test() {

        HttpResponse response = null;
        try {
            response = CoreHttpClient.makeGetRequest("https://amazon.in", null, HttpResponse.class);
        } catch (CoreException e) {
            e.printStackTrace();
        }
        Preconditions.checkArgument(ObjUtil.isBlank(null), "Invalid value");
    }

}