import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebformLearnerAutomation {

    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Automate Web Form Submission")
    @Test
    public void formAutomation() throws InterruptedException {

        // Step 1: Open the web form URL and verify the title
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        // Step 2: Fill in the form fields

        // Close the modal
        driver.findElements(By.tagName("button")).get(1).click();

        // Fill in the Name field
        driver.findElement(By.id("edit-name")).sendKeys("Test User");

        // Fill in the Phone Number field
        driver.findElement(By.id("edit-number")).sendKeys("1234567890");

        // Fill in the Date field with today's date (in MM/DD/YYYY format)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String todaysDate = LocalDate.now().format(formatter);
        driver.findElement(By.id("edit-date")).sendKeys(todaysDate);

        // Fill in the Email field
        driver.findElement(By.id("edit-email")).sendKeys("testuser@mail.com");

        // Fill in the "Tell us a bit about yourself" field (textarea)
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("This is a test submission for automation purposes.");

        // Wait to allow the form fields to load properly
        Thread.sleep(2000);

        // Step 3: Scroll down using your dynamic scroll method
        Utils.scroll(driver, 700);

        // Step 4: Upload a file (within 2MB limit)
        String relativePath = "\\src\\test\\resources\\testfile.doc";
        String absolutePath = System.getProperty("user.dir") + relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);

        // Wait for the file to upload
        Thread.sleep(2000);

        // Step 5: Interact with the Checkbox
        driver.findElement(By.id("edit-age")).click();

        // Step 6: Submit the form
        driver.findElement(By.id("edit-submit")).click();

        // Step 7: Assert that the success message is displayed after form submission
        WebElement successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your submission!')]"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Thank you for your submission!";

        // Verify the success message
        Assertions.assertEquals(expectedMessage, actualMessage, "Form submission message does not match!");
    }

    @AfterAll
    public void finishTest() {
        driver.quit();
    }
}
