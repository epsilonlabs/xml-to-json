# XML to JSON Transformation using Epsilon

This is a standalone Java application that demonstrates how to transform XML documents to JSON using Eclipse Epsilon's ETL (Epsilon Transformation Language) with plain XML and JSON drivers.

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