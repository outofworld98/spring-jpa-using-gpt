# Book Management Service

The Book Management Service is a Java-based web application built with Spring Boot that allows users to manage books and book categories. It provides RESTful APIs for adding, updating, and deleting books, as well as CRUD operations for book categories.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- Spring Security
- H2 Database (In-memory)
- Gradle (for build and dependency management)

## Features

- Add a new book with details such as ISBN, title, author, introduction, and tags.
- Update existing book information.
- Delete books from the database.
- Retrieve a list of all books or search for books based on keywords.
- Manage book categories, including adding books to specific categories.
- Secure APIs using Spring Security with role-based access (USER and MANAGER roles).

## Getting Started

### Prerequisites

- Java JDK 11 or higher installed
- Gradle installed

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/book-management-service.git
   cd book-management-service

2. Build and run the application:

   ```bash
    ./gradlew bootRun

## Usage

- Add a new book:

   ```bash
    curl -X POST -H "Content-Type: application/json" -d '{
    "isbn": "978-0061120084",
    "title": "To Kill a Mockingbird",
    "author": "Harper Lee",
    "introduce": "A Pulitzer Prize-winning novel about racial injustice and moral growth.",
    "tags": "classic, fiction"
    }' -u manager:password http://localhost:8080/books

- Update book details:
   ```bash
  curl -X PUT -H "Content-Type: application/json" -d '{
    "title": "Updated Title",
    "author": "Updated Author"
    }' -u manager:password http://localhost:8080/books/1
  
- Delete a book:
  ```bash
    curl -X DELETE -u manager:password http://localhost:8080/books/1
    
- Get all books:
  ```bash
  curl -u user:password http://localhost:8080/books

