# Cryptocurrency Prices Api
This application serves as a proxy backend server exposing api for cryptocurrency prices information.

## Meta info
Application showcases knowledge of:
- Java programming language
- Spring framework
  - Application setup and initialization
  - Annotation utilization
    - Controller and Service definition
    - Exposed requests mapping
    - Dependency injection (on constructor)
    - Cross origin handling
- Swagger (for application specification)
- General
  - Services defined as interfaces with separate implementation
  - REST calls
    - GET with request parameters
  - Calls to 3rd party api
  - Error handling
  - Helpers and utility classes with mostly static content
  - Having git commit messages readable
- Concepts
  - Separation of concerns
  - "N-tier architecture"
    - Controllers expose api endpoints
    - (Business) Logic and calls to 3rd party api is found inside services
- Operations
  - Setting environment variables (for production and development differentiation)
  - Deploying application

## Requirements
1. Java - 11.x
1. Maven - 3.x.x

## Development server
1. Build and run the backend app using maven  
    ```bash
    mvn package
    java -jar cryptocurrencypricesapi-0.0.1-SNAPSHOT.jar
    ```
    Alternatively, you can run the app without packaging it, using:
    ```bash
    mvn spring-boot:run
    ```
1. Provide environment variables:
  - CRYPTO_API_URL - url of the 3rd party api containing cryptocurrency prices information (example: https://sandbox-api.coinmarketcap.com)
  - CRYPTO_API_KEY - this key will be used as header (key: X-CMC_PRO_API_KEY) when making call to the 3rd party api
