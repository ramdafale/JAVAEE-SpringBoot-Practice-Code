# Spring Boot API Analyzer Agent

A comprehensive Python-based agent that analyzes Spring Boot applications to provide detailed information about API endpoints, request/response structures, mandatory fields, validation rules, and impact analysis for field changes.

## 🚀 Features

- **API Endpoint Analysis**: Automatically discovers and analyzes all REST endpoints
- **Request/Response Mapping**: Detailed analysis of request bodies, response types, path variables, and query parameters
- **Mandatory Field Detection**: Identifies required fields based on validation annotations
- **Validation Rules Analysis**: Extracts and analyzes all validation constraints
- **Impact Analysis**: Analyzes the impact of adding, removing, or modifying fields
- **Interactive Interface**: Natural language queries and command-based interface
- **Voice Commands**: Speech recognition and text-to-speech for hands-free interaction
- **CURL Command Generation**: Ready-to-use CURL commands for REST and GraphQL endpoints
- **GraphQL Support**: Complete GraphQL schema analysis and CURL generation
- **Internal API Integration Detection**: Analyzes RestTemplate, WebClient, and Feign client usage
- **Documentation Generation**: Automatic API documentation generation

## 📋 Requirements

- Python 3.7+
- A Spring Boot project with Java source files
- For voice functionality: Microphone and speakers
- Supported browsers for web interface: Chrome, Edge (for speech recognition)

## 🛠️ Installation

1. Clone or download the analyzer files:
   ```bash
   # Download the main analyzer
   wget https://raw.githubusercontent.com/your-repo/spring_boot_api_analyzer.py
   
   # Download the interactive interface
   wget https://raw.githubusercontent.com/your-repo/interactive_spring_agent.py
   ```

2. Install Python dependencies:
   ```bash
   # Basic functionality
   pip install pathlib dataclasses
   
   # For voice functionality
   pip install -r requirements.txt
   # OR manually:
   # pip install SpeechRecognition pyttsx3 pyaudio
   ```

## 🎯 Usage

### Command Line Analysis

```bash
# Analyze a Spring Boot project and output JSON report
python spring_boot_api_analyzer.py /path/to/your/spring-boot-project

# Generate markdown documentation
python spring_boot_api_analyzer.py /path/to/your/spring-boot-project --format markdown

# Save output to file
python spring_boot_api_analyzer.py /path/to/your/spring-boot-project --output analysis_report.json
```

### Interactive Mode

```bash
# Start interactive agent
python interactive_spring_agent.py /path/to/your/spring-boot-project

# With custom base URL
python interactive_spring_agent.py /path/to/project http://localhost:9090
```

### Voice-Enabled Mode

```bash
# Start voice-enabled agent
python voice_spring_agent.py /path/to/your/spring-boot-project

# With custom base URL
python voice_spring_agent.py /path/to/project http://localhost:9090
```

### Web Interface

```bash
# Open the web interface in your browser
open web_voice_interface.html
# OR
# Serve it with a local server for better functionality
python -m http.server 8000
# Then open: http://localhost:8000/web_voice_interface.html
```

## 🤖 Interactive Commands

Once in interactive mode, you can use these commands:

### General Queries
- `summary` - Show project summary with endpoint and model counts
- `endpoints` - List all API endpoints
- `models` - List all data models/DTOs
- `docs` - Generate complete API documentation

### Specific Queries
- `endpoints GET` - Filter endpoints by HTTP method
- `endpoints /users` - Find endpoints containing specific path
- `models UserDTO` - Get details about a specific model
- `mandatory UserDTO` - Get mandatory fields for a model
- `request /users POST` - Get request information for an endpoint
- `response /users GET` - Get response information for an endpoint
- `curl /users POST` - Get CURL command for an endpoint
- `curl all` - Get all CURL commands
- `impact UserDTO email remove` - Analyze impact of field changes

### Natural Language Queries
You can also ask questions in natural language:
- "What are the mandatory fields for User model?"
- "What does the POST /users endpoint expect?"
- "What happens if I add a new field to UserDTO?"
- "Show me all GET endpoints"
- "Give me the CURL command for POST /users"
- "Show me all CURL commands"
- "Generate GraphQL CURL command"
- "Show GraphQL schema structure"

### 🎤 Voice Commands (Voice Mode & Web Interface)
- "Show me the project summary" - Get project overview
- "Generate curl command for POST users" - Get CURL commands
- "Show GraphQL schema" - See GraphQL structure
- "List GraphQL queries" - Available GraphQL queries
- "GraphQL mutations available" - Available mutations
- "What fields are in User DTO" - See model structure
- "Show mandatory fields for create user request" - Required fields
- "Show internal integrations" - API dependencies
- "Impact of removing email field" - Change analysis
- "List all endpoints" - Show all API endpoints

## 📊 Example Output

### Endpoint Analysis
```
🌐 POST /api/users
   Controller: UserController.createUser
   Request Body: CreateUserRequest
   Response: ResponseEntity<UserDTO>
   Required Headers: Authorization
```

### Mandatory Fields Analysis
```
🔒 Mandatory fields for CreateUserRequest:
  • username
  • email
  • firstName
  • lastName
  • password
```

### Impact Analysis
```
🔍 Impact Analysis: Remove field 'email' in UserDTO

🌐 Affected Endpoints:
  • POST /api/users
  • PUT /api/users/{id}

⚠️  Validation Impact:
  • Removing mandatory field - will break validation

💡 Recommendations:
  • Ensure no client dependencies on this field
  • Consider deprecation period before removal
  • Update API documentation
```

### GraphQL CURL Commands
```bash
# GraphQL Query Example
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "query {\n  users(first: $first, filter: $filter) {\n    id\n    username\n    email\n    firstName\n    lastName\n  }\n}",
  "variables": {
    "first": 10,
    "filter": "active"
  }
}' \
  "http://localhost:8080/graphql"

# GraphQL Mutation Example
curl -X POST \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
  "query": "mutation {\n  createUser(input: $input) {\n    id\n    username\n    email\n  }\n}",
  "variables": {
    "input": {
      "username": "newuser",
      "email": "user@example.com",
      "firstName": "John",
      "lastName": "Doe",
      "password": "securePassword123"
    }
  }
}' \
  "http://localhost:8080/graphql"
```

## 🏗️ Architecture

The analyzer consists of several key components:

### Core Classes

- **`SpringBootApiAnalyzer`**: Main analysis engine that parses Java files
- **`SpringBootApiAgent`**: High-level interface for querying analysis results
- **`InteractiveSpringBootAgent`**: Interactive command-line interface
- **`ApiEndpoint`**: Data structure for endpoint information
- **`ModelInfo`**: Data structure for model/DTO information
- **`FieldInfo`**: Data structure for field details and validation rules

### Analysis Process

1. **File Discovery**: Recursively finds all `.java` files in the project
2. **Classification**: Identifies controllers vs models/DTOs using annotations and patterns
3. **Controller Analysis**: Extracts endpoint mappings, HTTP methods, and parameter information
4. **Model Analysis**: Parses field declarations, validation annotations, and inheritance
5. **Relationship Mapping**: Links endpoints to their request/response models
6. **Report Generation**: Compiles comprehensive analysis data

## 🔍 Supported Annotations

### Controller Annotations
- `@RestController`, `@Controller`
- `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`
- `@RequestBody`, `@PathVariable`, `@RequestParam`, `@RequestHeader`

### Validation Annotations
- `@NotNull`, `@NotEmpty`, `@NotBlank`
- `@Size`, `@Min`, `@Max`
- `@Pattern`, `@Email`
- `@Past`, `@Future`, `@PastOrPresent`, `@FutureOrPresent`
- `@Positive`, `@PositiveOrZero`, `@Negative`, `@NegativeOrZero`

### Model Annotations
- `@Entity`, `@Document`, `@Embeddable`
- `@Valid`, `@Required`

## 🎯 Use Cases

### API Documentation
Generate comprehensive documentation for your Spring Boot APIs automatically.

### Code Review
Quickly understand API structure and identify potential issues before code review.

### Impact Analysis
Assess the impact of model changes on existing endpoints and client integrations.

### Onboarding
Help new team members understand the API structure and requirements.

### Testing
Identify all endpoints and their requirements for comprehensive test coverage.

### Client Integration
Provide detailed information about API contracts for frontend/mobile developers.

## 🔧 Example Project Structure

The analyzer works with standard Spring Boot project structures:

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               └── demo/
│                   ├── controller/
│                   │   └── UserController.java
│                   ├── dto/
│                   │   ├── UserDTO.java
│                   │   └── CreateUserRequest.java
│                   └── entity/
│                       └── User.java
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues, feature requests, or pull requests.

### Areas for Enhancement
- Support for additional validation frameworks
- GraphQL endpoint analysis
- OpenAPI/Swagger integration
- Database schema analysis
- Security annotation analysis

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Troubleshooting

### Common Issues

**Issue**: "No endpoints found"
- **Solution**: Ensure your controllers use standard Spring annotations (`@RestController`, `@RequestMapping`, etc.)

**Issue**: "Model not found"
- **Solution**: Check that your DTOs/entities are in the analyzed directory and use proper Java class syntax

**Issue**: "Validation rules not detected"
- **Solution**: Ensure you're using standard JSR-303/JSR-380 validation annotations

### Debug Mode

Run with verbose output to see detailed parsing information:
```bash
python spring_boot_api_analyzer.py /path/to/project --verbose
```

## 📞 Support

For questions, issues, or feature requests, please create an issue in the GitHub repository or contact the maintainers.

---

**Happy API Analysis!** 🎉
