# Java Assignment 2

This is a Java project that uses **TestNG**, **Selenium WebDriver**, and **WebDriverManager** to automate code generation and testing across Copilot and CodeChef.

---

## Features

- Queries Microsoft Copilot for Python code generation
- Copies and executes the code on CodeChef IDE
- Captures the output and creates an HTML test report
- Uses TestNG for structured test execution

---

## Requirements

Make sure the following tools are installed:

- **Java 24**
- **Maven 3.6.3 or newer**
- **Any IDE** (IntelliJ IDEA recommended)

---

## How to Run

### From IntelliJ IDEA

- Right-click on `testng.xml` → **Run**
- Or run `Java_Assignment2_Test.java` directly

### From Terminal

```bash
mvn clean test
```

---

## Dependencies

The project uses the following dependencies. These are defined in the `pom.xml` file:

- **Selenium**: For browser automation
  ```xml
  <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.20.0</version>
  </dependency>
  ```

- **WebDriverManager**: To automatically manage browser drivers
  ```xml
  <dependency>
      <groupId>io.github.bonigarcia</groupId>
      <artifactId>webdrivermanager</artifactId>
      <version>5.8.0</version>
  </dependency>
  ```

- **TestNG**: For test framework support
  ```xml
  <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.9.0</version>
      <scope>test</scope>
  </dependency>
  ```

---

## Technologies Used

- Java  
- Selenium  
- WebDriverManager  
- TestNG  
- Maven

---

## Project Structure

```
Java_Assignment2/
├── pom.xml                          # Maven dependencies and config
├── testng.xml                       # TestNG suite config
├── AddFunction_assignment1.py       # Copied code from Copilot
├── TestReport_assignment1.html      # Generated test report
├── src/
│   ├── main/java/org/example/       # Optional helpers/utilities
│   └── test/java/org/example/       # Main TestNG test class
│       └── Java_Assignment2_Test.java
```

---

## Output

- **AddFunction_assignment1.py**: Generated Python function from Copilot
- **TestReport_assignment1.html**: Detailed test report including execution status and runtime info

---

## License

This project is for educational purposes.
