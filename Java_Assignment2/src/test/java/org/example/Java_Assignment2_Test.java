package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.logging.Logger;

public class Java_Assignment2_Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private long startTime;
    private static final Logger logger = Logger.getLogger(Java_Assignment2_Test.class.getName());

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        startTime = System.currentTimeMillis();
    }

    @Test(priority = 1)
    public void fetchPythonCodeFromCopilot() throws InterruptedException, IOException, UnsupportedFlavorException {
        driver.get("https://copilot.microsoft.com/chat");

        WebElement messageBox = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("textarea")));
        messageBox.sendKeys("Please provide a Python function that accepts space-separated numbers from the user input (stdin), performs addition on those numbers,.This should return and print result. Do not include any comments.");
        messageBox.sendKeys(Keys.RETURN);

        Thread.sleep(5000);
        WebElement copyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Copy code']")));
        copyButton.click();

        String code = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        Files.write(Paths.get("AddFunction_assignment1.py"), code.getBytes());
        logger.info("Code saved to file.");
        Thread.sleep(5000);
    }

    @Test(priority = 2, dependsOnMethods = "fetchPythonCodeFromCopilot")
    public void runCodeOnCodeChef() throws IOException, InterruptedException {
        driver.get("https://www.codechef.com/ide");
        Thread.sleep(8000);

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("language-select")));
        dropdown.click();
        WebElement pythonOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Python3']")));
        pythonOption.click();
        Thread.sleep(5000);

        String pythonCode = Files.readString(Paths.get("AddFunction_assignment1.py"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String escapedCode = pythonCode.replace("\\", "\\\\").replace("\n", "\\n").replace("\"", "\\\"").replace("\r", "");

        js.executeScript("ace.edit(document.getElementsByClassName('ace_editor')[0]).setValue(\"" + escapedCode + "\");");
        Thread.sleep(5000);

        WebElement inputBox = driver.findElement(By.xpath("//div[contains(@class, '_tcContainer_1mvh4_454')]"));
        WebElement stdinBox = inputBox.findElement(By.tagName("textarea"));
        stdinBox.sendKeys("40 20");
        Thread.sleep(5000);

        WebElement runBtn = driver.findElement(By.xpath("//div[@class='_execute-btn-container_1mvh4_215']"));
        runBtn.findElement(By.tagName("button")).click();

        Thread.sleep(5000);

        WebElement outputBox = driver.findElement(By.xpath("//div[contains(@class, '_dark_58rxo_263')]"));
        String rawOutput = outputBox.getText();

        String status = extract(rawOutput, "Status :", "Time:");
        String time = extract(rawOutput, "Time:", "Memory:");
        String memory = extract(rawOutput, "Memory:", "Sample Input");
        String sampleInput = extract(rawOutput, "Sample Input", "Your Output");
        String yourOutput = rawOutput.substring(rawOutput.indexOf("Your Output") + "Your Output".length()).trim();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        String htmlReport = "<html><head><title>Test Execution Report</title></head><body>" +
                "<h1>Test Execution Report</h1>" +
                "<table border='1' cellpadding='10'>" +
                "<tr><th>Test Case</th><td>Addition Function Test</td></tr>" +
                "<tr><th>Status</th><td>" + status + "</td></tr>" +
                "<tr><th>Execution Time (ms)</th><td>" + duration + "</td></tr>" +
                "<tr><th>Time (Output)</th><td>" + time + "</td></tr>" +
                "<tr><th>Memory Used (Runtime)</th><td>" + memoryUsed + " bytes</td></tr>" +
                "<tr><th>Memory (Output)</th><td>" + memory + "</td></tr>" +
                "<tr><th>Sample Input</th><td>" + sampleInput + "</td></tr>" +
                "<tr><th>Your Output</th><td>" + yourOutput + "</td></tr>" +
                "</table></body></html>";

        Files.write(Paths.get("TestReport_assignment1.html"), htmlReport.getBytes());
        logger.info("HTML report saved.");
    }

    private String extract(String text, String start, String end) {
        return text.substring(text.indexOf(start) + start.length(), text.indexOf(end)).trim();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
