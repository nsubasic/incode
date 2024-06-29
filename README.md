# Text Transformer

This project is a web application built using Spring Boot that allows for transforming string values based on configurable transformers.

## Setup Instructions

### Prerequisites

- **Java 15 or higher**: Ensure you have Java Development Kit (JDK) 15 or later installed.
- **Gradle 6.8 or higher**: Make sure Gradle is installed on your machine.
- **Git**: For cloning the repository.

### Clone the Repository

1. Open your terminal or command prompt.
2. Run the following command to clone the repository:
   ```sh
   git clone https://github.com/nsubasic/incode.git
   cd incode

### Build and Run the Project

1. Build the project using Gradle:
   ```sh
   ./gradlew clean build
2. Run the application:
   ```sh
   ./gradlew bootRun
The application will start on http://localhost:8080.

### Accessing the API Documentation
Once the application is running, you can access the API documentation at:

    http://localhost:8080/swagger-ui/index.html

### Example API Request

You can use tools like curl, Postman, or any HTTP client to make a POST request to transform elements.

#### Example JSON Payload
POST /transform

Content-Type: application/json

```json

 [
    {
      "value": "Hello, 123 world!",
      "transformers": [
        {
          "transformerId": "removeRegex",
          "parameters": {
            "regex": "\\d+"
          }
        },
        {
          "transformerId": "replaceRegex",
          "parameters": {
            "regex": "world",
            "replacement": "универсе"
          }
        },
        {
          "transformerId": "transliterate",
          "parameters": {}
        }
      ]
    }
  ]

```
#### Example Response
   ```json

   {
     "originalValue": "Hello, 123 world!",
     "transformedValue": "Hello,  universe!"
   }
   ```
### Parameters Field in Transformer

The `parameters` field in the Transformer is a `Map<String, String>` used to pass additional information required by the transformers.

- When `transformerId` is `removeRegex`, the `parameters` map should have a key `"regex"` which specifies the regex pattern to remove.
- When `transformerId` is `replaceRegex`, the `parameters` map should have keys `"regex"` and `"replacement"` which specify the regex pattern to replace and the replacement string, respectively.
- The `parameters` map exists but is not used when `transformerId` is `transliterate`.

### Transformer Strategy

The `transformerId` in the Transformer is used to determine the `TransformerStrategy`, which indicates which transformer will be used. The application supports three types of transformers:

- `removeRegex`: Removes matches of the specified regex from the string value.
- `replaceRegex`: Replaces matches of the specified regex with the provided replacement.
- `transliterate`: Transliterates Cyrillic and Greek letters to Latin.


### Running Tests
To run the tests, use the following command:
```sh
./gradlew test
