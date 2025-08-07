# Project Name

## Overview

This is a simple microservice project that was created to practice my DHL E-Commerce interview. It takes an order and returns the rate of the package. This project sets some good standards of Java 21, SpringBoot, JPA, and Docker.
## Prerequisites

- Java 21 (or your project's required version)
- Gradle 7.x+
- Docker & Docker Compose
- (Optional) Kotlin plugin for your IDE

## Setup

### 1. Clone the repository

```sh
git clone https://github.com/machine88/order.git
cd your-repo
```
### 2. Build the project

```sh
./gradlew clean build
```
### 3. Run the application

```sh
./gradlew bootRun
```
### 4. Run tests

```sh
./gradlew test
```
### 5. Build and run with Docker

```sh
docker-compose up --build
```
### 6. Stop and remove Docker containers

```sh
docker-compose down
```
### 7. View application logs

```sh
docker-compose logs -f
```
## API Endpoints

### Create Order

- **POST** `/api/orders`
- **Request Body** (JSON):
  ```json
  {
    "customerName": "John Doe",
    "destinationCountry": "DE",
    "serviceLevel": "EXPRESS",
    "packages": [
      {
        "weightKg": 2.5,
        "lengthCm": 30.0,
        "widthCm": 20.0,
        "heightCm": 10.0
      },
      {
        "weightKg": 1.2,
        "lengthCm": 15.0,
        "widthCm": 10.0,
        "heightCm": 5.0
      }
    ]
  }
  ```
  ### Retrieve Order

- **GET** `/api/orders/{orderId}`
- **Response** (`200 OK`):
  ```json
  {
    "orderId": 123,
    "customerName": "John Doe",
    "destinationCountry": "DE",
    "serviceLevel": "EXPRESS",
    "packages": [
      {
        "weightKg": 2.5,
        "lengthCm": 30.0,
        "widthCm": 20.0,
        "heightCm": 10.0
      },
      {
        "weightKg": 1.2,
        "lengthCm": 15.0,
        "widthCm": 10.0,
        "heightCm": 5.0
      }
    ],
    "rate": 12.99,
    "currency": "EUR",
    "status": "CREATED"
  }
  ```
  