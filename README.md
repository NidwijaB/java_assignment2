# Java_Assignment2

## 🧪 Automated TestNG + Selenium Framework

This project uses **TestNG**, **Selenium WebDriver**, and **WebDriverManager** to automate the following:
- Query Copilot (https://copilot.microsoft.com) for Python code.
- Copy and save the generated code.
- Run it on CodeChef IDE (https://www.codechef.com/ide).
- Capture execution output and generate an HTML test report.

---

## 📁 Project Structure

```
Java_Assignment2/
│
├── pom.xml                          # Maven dependencies and config
├── testng.xml                       # TestNG suite config
│
├── AddFunction_assignment1.py       # Copied code from Copilot
├── TestReport_assignment1.html      # Generated test report
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               └── (optional helpers/utilities)
│   └── test/
│       └── java/
│           └── org/
│               └── example/
│                   └── Java_Assignment2_Test.java     # Main TestNG test class
```

---

## 🛠 Dependencies

All dependencies are managed via Maven. Key ones include:

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.20.0</version>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.8.0</version>
    </dependency>
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## ▶️ How to Run

### From IntelliJ IDEA
- Right-click `testng.xml` → **Run**.
- Or open `Java_Assignment2_Test.java` and run it directly.

### From Terminal
```bash
mvn clean test
```

---

## 📋 Output

- `AddFunction_assignment1.py`: The generated Python function.
- `TestReport_assignment1.html`: Report with execution status, input/output, runtime & memory info.

---

## ✅ Features
- Automates end-to-end flow from Copilot to CodeChef.
- Uses TestNG lifecycle: `@BeforeClass`, `@Test`, `@AfterClass`.
- Generates a custom HTML report.