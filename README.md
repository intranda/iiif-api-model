# iiif-api-model

This is the Java model for IIIF APIs, used in the [Goobi
Viewer](https://github.com/intranda/goobi-viewer-core). 

You can use this model to create IIIF manifests in Java and serialize
them to valid IIIF JSON-LD. 

To deserialize a JSON stream, the Jackson `ObjectMapper` has to be
configured as follows:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
```

