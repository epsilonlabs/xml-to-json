# XML to JSON Transformation using Eclipse Epsilon

This is a standalone Java application that demonstrates how to transform XML documents to JSON using Eclipse Epsilon's ETL (Epsilon Transformation Language) with plain XML and JSON drivers.

## Project Structure

```
xml-to-json/
├── pom.xml                                    # Maven configuration
├── src/
│   └── main/
│       ├── java/
│       │   └── org/
│       │       └── example/
│       │           └── XmlToJsonTransformation.java  # Main application
│       └── resources/
│           ├── library.xml                    # Sample XML input
│           └── transformation.etl             # ETL transformation script
└── README.md
```

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

## How It Works

1. **library.xml**: Contains a sample library with books, authors, and publication details
2. **transformation.etl**: Defines ETL transformation rules using Epsilon's JSON driver:
   - `Book2JsonBook`: Transforms each XML `<book>` element to a JSONObject with title, authors array, year, and ISBN
   - `Library2JsonRoot`: Creates the root JSON structure with a books array containing all transformed books
   - `post` block: Sets the JSON model root (required for JSON output)
3. **XmlToJsonTransformation.java**:
   - Loads the XML source model using `PlainXmlModel`
   - Initializes the JSON target model using `JsonModel` with `setStoredOnDisposal(true)` to save on exit
   - Parses and executes the ETL transformation
   - Saves the result to `output.json`

## Key Concepts

### Epsilon JSON Driver Types

The JSON driver provides two main types:
- **`JSONObject`**: For creating JSON objects (key-value pairs)
- **`JSONArray`**: For creating JSON arrays

### Setting Properties

```etl
// Set a simple property
jsonObj.propertyName = "value";

// Create and populate an array
jsonObj.items = new Target!JSONArray;
jsonObj.items.add("item1");
jsonObj.items.addAll(collection);
```

### Important: Setting the Root

Unlike EMF models, JSON models require explicitly setting the root element in a `post` block:

```etl
post {
    Target.root = rootElement.equivalent();
}
```

## Output

The transformation produces a `output.json` file in the project root:

```json
{
  "books": [
    {
      "year": "1999",
      "isbn": "978-0201616224",
      "title": "The Pragmatic Programmer",
      "authors": ["Andrew Hunt", "David Thomas"]
    },
    {
      "year": "2008",
      "isbn": "978-0132350884",
      "title": "Clean Code",
      "authors": ["Robert C. Martin"]
    },
    {
      "year": "1994",
      "isbn": "978-0201633610",
      "title": "Design Patterns",
      "authors": ["Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"]
    }
  ]
}
```

## Eclipse Epsilon

Eclipse Epsilon is a family of languages and tools for model management. This example uses:

- **Plain XML Driver** (`org.eclipse.epsilon.emc.plainxml`): For reading XML files as models without requiring XML schemas
- **JSON Driver** (`org.eclipse.epsilon.emc.json`): For creating and writing JSON files using JSONObject and JSONArray types
- **ETL (Epsilon Transformation Language)**: For defining model-to-model transformations with declarative rules

### Dependencies

All Epsilon dependencies are fetched from Maven Central:
- `org.eclipse.epsilon.eol.engine` (v2.8.0): Core EOL language engine
- `org.eclipse.epsilon.etl.engine` (v2.8.0): ETL transformation engine
- `org.eclipse.epsilon.emc.plainxml` (v2.8.0): Plain XML model driver
- `org.eclipse.epsilon.emc.json` (v2.8.0): JSON model driver

## Learn More

- [Eclipse Epsilon Documentation](https://eclipse.dev/epsilon/doc/)
- [ETL Language Guide](https://eclipse.dev/epsilon/doc/etl/)
- [Scripting JSON with Epsilon](https://eclipse.dev/epsilon/doc/articles/json-emc/)
- [Plain XML Driver Documentation](https://eclipse.dev/epsilon/doc/articles/plain-xml/)
