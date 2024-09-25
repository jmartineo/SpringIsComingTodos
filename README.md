# Spring Is Coming Todos

A small project using Spring Boot which consists of a TODO application with numerous features, using MariaDB as a database.

## Current Features

- [x] Create a new TODO
- [x] Update a TODO
- [x] Delete a TODO
- [x] List all TODOs
- [x] List all TODOs by status (Completed or Incomplete)

## Prerequisites

- Java 17
- Maven
- MariaDB

## Getting Started

#### 1. **Clone the repository**

```shell
git clone https://github.com/jmartineo/SpringIsComingTodos.git
```

#### 2. **Navigate to the project directory**

```shell
cd springtodos
```

#### 3. **Configure the database**

- Application Properties (modify the ```application.properties``` file to fit your needs)

- 

#### 4. **Build and run the application**

- Using Maven and Java natively

```shell
mvn spring-boot:run
```

- Using Docker:

```shell
docker-compose build
```

```shell
docker-compose up
```

To stop ```docker-compose down```

#### 5. **Access the application at ```localhost:8080```**
