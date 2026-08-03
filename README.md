# XML to JSON Transformation using Epsilon

This is a standalone Java application that demonstrates how to transform XML documents to JSON 
using the [Epsilon Transformation Language](https://eclipse.dev/epsilon/doc/etl/) with Epsilon's
[plain XML](https://eclipse.dev/epsilon/doc/articles/plain-xml/) and 
[JSON](https://eclipse.dev/epsilon/doc/articles/json-emc/) drivers.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean package
```

## Running the Transformation

```bash
mvn exec:java
```

Or after building, you can run:

```bash
java -cp target/classes:~/.m2/repository/... org.example.XmlToJsonTransformation
```