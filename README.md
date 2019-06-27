# iiif-api-model: Java library to create and consume IIIF manifests

## Introduction
This is a Java library to to create and consume IIIF Presentation v2 manifests in Java and serialize them to valid IIIF JSON-LD. It is used in various applications within the open source software suite Goobi.



## Installation
This library is published in the intranda Nexus server. You can use it by adding the following snippets to your pom.xml:
```xml
<repositories>
  <repository>
    <id>intranda-public</id>
    <url>https://nexus.intranda.com/repository/maven-public</url>
  </repository>
</repositories>

<dependency>
  <groupId>de.intranda.api.iiif</groupId>
  <artifactId>iiif-api-model</artifactId>
  <version>1.1.6</version>
</dependency>
```

### Examples
To deserialize a JSON stream, the Jackson `ObjectMapper` has to be configured as follows:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
```



## Licence
The iiif-api-model is released under the license GPL2 or later. Please see LICENSE for more information.



## Contributing
- Fork it (https://github.com/intranda/iiif-api-model/fork)
- Create your feature branch (git checkout -b feature/fooBar)
- Commit your changes (git commit -am 'Add some fooBar')
- Push to the branch (git push origin feature/fooBar)
- Create a new Pull Request
