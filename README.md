
## Java Core Util Features

## Http Client

```java

HttpResponse response = null;
        try {
            response = CoreHttpClient.makeGetRequest("https://amazon.in", null, HttpResponse.class);
        } catch (JavaCoreException e) {
            e.printStackTrace();
        }

```