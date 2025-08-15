# Spring Boot API Documentation

## Summary
- **Total Endpoints:** 3
- **Total Models:** 2
- **Endpoints by Method:** {'GET': 1, 'PUT': 1, 'DELETE': 1}

## API Endpoints

### GET /api/users/{id}
- **Controller:** UserController
- **Method:** getUserById
- **Response Type:** ResponseEntity<UserDTO>

### PUT /api/users/{id}
- **Controller:** UserController
- **Method:** updateUser
- **Response Type:** ResponseEntity<UserDTO>

### DELETE /api/users/{id}
- **Controller:** UserController
- **Method:** deleteUser
- **Response Type:** ResponseEntity<Void>

## Data Models

### UserDTO
- **Package:** com.example.demo.dto
- **Fields:**
  - `id`: Long
  - `username`: String - Validations: @Size
  - `email`: String - Validations: @Email
  - `firstName`: String (Mandatory) - Validations: @NotBlank
  - `lastName`: String (Mandatory) - Validations: @NotBlank
  - `department`: String
  - `birthDate`: LocalDateTime - Validations: @Past
  - `phoneNumber`: String - Validations: @Pattern
  - `active`: boolean
  - `createdAt`: LocalDateTime
  - `updatedAt`: LocalDateTime
  - `id`: return
  - `username`: return
  - `email`: return
  - `firstName`: return
  - `lastName`: return
  - `department`: return
  - `birthDate`: return
  - `phoneNumber`: return
  - `active`: return
  - `createdAt`: return
  - `updatedAt`: return

### CreateUserRequest
- **Package:** com.example.demo.dto
- **Fields:**
  - `username`: String - Validations: @Size
  - `email`: String - Validations: @Email
  - `firstName`: String - Validations: @Size
  - `lastName`: String - Validations: @Size
  - `password`: String - Validations: @Size
  - `department`: String
  - `birthDate`: LocalDateTime - Validations: @Past
  - `phoneNumber`: String - Validations: @Pattern
  - `username`: return
  - `email`: return
  - `firstName`: return
  - `lastName`: return
  - `password`: return
  - `department`: return
  - `birthDate`: return
  - `phoneNumber`: return
