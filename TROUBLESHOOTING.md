# 🚨 Tennis Prediction Application - Troubleshooting Guide

## Common Issues and Solutions

### 1. **Java Version Issues**

**Problem:** Application won't start due to Java version incompatibility

**Solution:**
```bash
# Check your Java version
java -version

# Required: Java 17 or higher (preferably Java 21)
# If you have multiple Java versions:
java -version
which java
echo $JAVA_HOME

# Set JAVA_HOME if needed:
export JAVA_HOME=/path/to/your/java
export PATH=$JAVA_HOME/bin:$PATH
```

**Download Java 21:**
- **Windows:** Download from Oracle or use OpenJDK
- **macOS:** `brew install openjdk@21`
- **Linux:** `sudo apt install openjdk-21-jdk`

### 2. **Maven Issues**

**Problem:** Maven not found or version issues

**Solution:**
```bash
# Check Maven version
mvn -version

# Required: Maven 3.6+ (preferably 3.9+)

# Install Maven if missing:
# Windows: Download from Apache Maven website
# macOS: brew install maven
# Linux: sudo apt install maven
```

### 3. **Port Already in Use**

**Problem:** `Port 8080 is already in use`

**Solution:**
```bash
# Check what's using port 8080
# Windows: netstat -ano | findstr :8080
# macOS/Linux: lsof -i :8080

# Kill the process or change port in application.properties:
server.port=8081
```

### 4. **Build Failures**

**Problem:** `mvn clean compile` fails

**Solution:**
```bash
# Clean and rebuild
mvn clean
mvn compile

# If still failing, check:
# 1. Internet connection (for downloading dependencies)
# 2. Maven settings.xml
# 3. Java version compatibility
```

### 5. **Database Issues**

**Problem:** H2 database connection errors

**Solution:**
```bash
# The application uses in-memory H2 database
# No setup required, but check application.properties:

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:tennisdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### 6. **Missing Dependencies**

**Problem:** ClassNotFoundException or NoClassDefFoundError

**Solution:**
```bash
# Download all dependencies
mvn dependency:resolve

# Force download
mvn clean install -U
```

### 7. **IDE Issues**

**Problem:** IDE can't find classes or dependencies

**Solution:**
- **IntelliJ IDEA:** File → Invalidate Caches and Restart
- **Eclipse:** Project → Clean
- **VS Code:** Reload window and ensure Java extension is installed

### 8. **Application Won't Start**

**Problem:** Spring Boot application fails to start

**Solution:**
```bash
# Check logs for specific errors
mvn spring-boot:run

# Common issues:
# 1. Port already in use
# 2. Database connection issues
# 3. Missing dependencies
# 4. Java version incompatibility
```

### 9. **API Endpoints Not Working**

**Problem:** 404 errors when accessing API endpoints

**Solution:**
```bash
# Check the correct URL structure:
# Base URL: http://localhost:8080/tennis-prediction
# API: http://localhost:8080/tennis-prediction/api/matches

# Test with curl:
curl http://localhost:8080/tennis-prediction/api/matches
```

### 10. **Web Dashboard Issues**

**Problem:** Dashboard shows errors or doesn't load

**Solution:**
```bash
# Check if Thymeleaf templates are in correct location:
# src/main/resources/templates/dashboard.html

# Verify template syntax and dependencies
```

## 🔧 Step-by-Step Setup Guide

### **Prerequisites:**
1. **Java 21** (or Java 17+)
2. **Maven 3.9+**
3. **Git** (for cloning)

### **Setup Steps:**

```bash
# 1. Clone or download the project
git clone <repository-url>
cd tennis-prediction-app

# 2. Verify Java and Maven
java -version
mvn -version

# 3. Clean and build
mvn clean compile

# 4. Run the application
mvn spring-boot:run

# 5. Test the application
curl http://localhost:8080/tennis-prediction/api/matches
```

### **Verification Steps:**

1. **Check Application Startup:**
   ```bash
   # Look for these messages in logs:
   # "Started TennisPredictionApplication"
   # "H2 console available at '/tennis-prediction/h2-console'"
   ```

2. **Test API Endpoints:**
   ```bash
   # Test matches API
   curl http://localhost:8080/tennis-prediction/api/matches
   
   # Test players API
   curl http://localhost:8080/tennis-prediction/api/players
   
   # Test prediction API
   curl -X POST http://localhost:8080/tennis-prediction/api/predictions/match-winner/1
   ```

3. **Access Web Dashboard:**
   - Open browser: `http://localhost:8080/tennis-prediction/`

## 🆘 Still Having Issues?

### **Debug Mode:**
```bash
# Run with debug logging
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dlogging.level.com.tennis=DEBUG"
```

### **Common Error Messages:**

1. **"Port 8080 already in use"**
   - Change port in `application.properties`
   - Kill existing process

2. **"No compiler is provided in this environment"**
   - Install JDK (not just JRE)
   - Set JAVA_HOME correctly

3. **"Could not resolve dependencies"**
   - Check internet connection
   - Clear Maven cache: `mvn dependency:purge-local-repository`

4. **"ClassNotFoundException"**
   - Run `mvn clean install`
   - Check if all dependencies are downloaded

### **System-Specific Issues:**

**Windows:**
- Use Command Prompt or PowerShell as Administrator
- Check Windows Defender/firewall settings
- Ensure Java is in PATH

**macOS:**
- Use Terminal
- Install Java via Homebrew: `brew install openjdk@21`
- Set JAVA_HOME: `export JAVA_HOME=$(/usr/libexec/java_home -v 21)`

**Linux:**
- Use terminal
- Install Java: `sudo apt install openjdk-21-jdk`
- Set JAVA_HOME if needed

## 📞 Need More Help?

If you're still experiencing issues, please provide:
1. **Operating System** (Windows/macOS/Linux)
2. **Java version** (`java -version`)
3. **Maven version** (`mvn -version`)
4. **Exact error message** from the console
5. **Steps you followed** to reproduce the issue

This will help provide more specific solutions for your environment.