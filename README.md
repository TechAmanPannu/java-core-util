
## Java Core Util Features

1. Http Client
2. Preconditions
3. Custom Exceptions
4. Object Utilities
5. Hash Utilities
6. QueryParams
7. ThreadUtil



## Http Client
```java

HttpResponse response = null;
        try {
            response = CoreHttpClient.makeGetRequest("https://amazon.in", null, HttpResponse.class);
        } catch (CoreException e) {
            e.printStackTrace();
        }

```
## Preconditions

````java

Preconditions.checkArgument(ObjUtil.isBlank(null), "Invalid value");

/// IllegalArgException has to configure in order to use this. Check Springboot Exception Handling
````