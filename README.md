# Age Calculation API

A Java Spring Boot REST API that calculates a person's age from their date of birth.

## Features

- Calculate age from a date of birth
- Support for GET and POST requests
- Validation for invalid or future dates
- JSON response format

## Endpoints

### 1) GET /api/v1/age

Request:

```bash
curl "http://localhost:8080/api/v1/age?dateOfBirth=1998-08-15"
```

Response:

```json
{
  "dateOfBirth": "1998-08-15",
  "age": 27
}
```

### 2) POST /api/v1/age

Request:

```bash
curl -X POST http://localhost:8080/api/v1/age \
  -H "Content-Type: application/json" \
  -d '{"dateOfBirth":"2000-02-29"}'
```

Response:

```json
{
  "dateOfBirth": "2000-02-29",
  "age": 24
}
```

## Run the application

```bash
mvn spring-boot:run
```

Then access the API at:

```text
http://localhost:8080
```

## Tech Stack

- Java 17
- Spring Boot 3.3.5
- Maven

## Project structure

```text
src/
  main/
    java/
      com/
        kayal/
          ageapi/
            AgeCalculationApiApplication.java
            controller/
              AgeController.java
            dto/
              AgeRequest.java
              AgeResponse.java
              ApiErrorResponse.java
            exception/
              ApiExceptionHandler.java
            service/
              AgeService.java
  test/
    java/
      com/
        kayal/
          ageapi/
            AgeControllerTest.java
```
