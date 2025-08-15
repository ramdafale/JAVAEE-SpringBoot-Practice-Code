# Spring Boot API Documentation

## Summary
- **Total Endpoints:** 9
- **Total Models:** 2
- **Endpoints by Method:** {'GET': 1, 'PUT': 1, 'DELETE': 1, 'POST': 6}

## API Endpoints

### GET /api/users/{id}
- **Controller:** UserController
- **Method:** getUserById
- **Response Type:** ResponseEntity<UserDTO>

**CURL Command:**
```bash
curl -X GET \
  "http://localhost:8080/api/users/{id}"
```

### PUT /api/users/{id}
- **Controller:** UserController
- **Method:** updateUser
- **Response Type:** ResponseEntity<UserDTO>

**CURL Command:**
```bash
curl -X PUT \
  "http://localhost:8080/api/users/{id}"
```

### DELETE /api/users/{id}
- **Controller:** UserController
- **Method:** deleteUser
- **Response Type:** ResponseEntity<Void>

**CURL Command:**
```bash
curl -X DELETE \
  "http://localhost:8080/api/users/{id}"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** users
- **Response Type:** List<UserDTO>

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "query {\n  users(first: $first, filter: $filter) {\n    id\n    name\n  }\n}",
  "variables": {
    "first": "sample_first",
    "filter": "sample_filter"
  }
}' \
  "http://localhost:8080/graphql"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** user
- **Response Type:** UserDTO

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "query {\n  user(id: $id, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "id": "1",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** createUser
- **Response Type:** UserDTO

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  createUser(input: $input, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "input": "sample_input",
    "id": "1",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** updateUser
- **Response Type:** UserDTO

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  updateUser(id: $id, input: $input, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "id": "1",
    "input": "sample_input",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** deleteUser
- **Response Type:** Boolean

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  deleteUser(id: $id)\n}",
  "variables": {
    "id": "1"
  }
}' \
  "http://localhost:8080/graphql"
```

### POST /graphql
- **Controller:** UserGraphQLController
- **Method:** userUpdates
- **Response Type:** Flux<UserDTO>

**CURL Command:**
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "subscription {\n  userUpdates(userId: $id) {\n    id\n    name\n  }\n}",
  "variables": {
    "id": "1"
  }
}' \
  "http://localhost:8080/graphql"
```

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

## CURL Commands

Ready-to-use CURL commands for all endpoints:

### GET /api/users/{id}
```bash
curl -X GET \
  "http://localhost:8080/api/users/{id}"
```

### PUT /api/users/{id}
```bash
curl -X PUT \
  "http://localhost:8080/api/users/{id}"
```

### DELETE /api/users/{id}
```bash
curl -X DELETE \
  "http://localhost:8080/api/users/{id}"
```

### GraphQL query users
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "query {\n  users(first: $first, filter: $filter) {\n    id\n    name\n  }\n}",
  "variables": {
    "first": "sample_first",
    "filter": "sample_filter"
  }
}' \
  "http://localhost:8080/graphql"
```

### GraphQL query user
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "query {\n  user(id: $id, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "id": "1",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### GraphQL mutation createUser
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  createUser(input: $input, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "input": "sample_input",
    "id": "1",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### GraphQL mutation updateUser
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  updateUser(id: $id, input: $input, id: $id, username: $name, email: $email, firstName: $name, lastName: $name) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "id": "1",
    "input": "sample_input",
    "username": "sampleuser",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}' \
  "http://localhost:8080/graphql"
```

### GraphQL mutation deleteUser
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  deleteUser(id: $id)\n}",
  "variables": {
    "id": "1"
  }
}' \
  "http://localhost:8080/graphql"
```

### GraphQL subscription userUpdates
```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "subscription {\n  userUpdates(userId: $id) {\n    id\n    name\n  }\n}",
  "variables": {
    "id": "1"
  }
}' \
  "http://localhost:8080/graphql"
```
