import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRegistrationForm {

    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Automate Guest Registration Form Submission")
    @Test
    public void formAutomation() throws InterruptedException {

        // Step 1: Open the web form URL
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

        // Step 2: Fill in the form fields

        // First Name
        driver.findElement(By.id("first_name")).sendKeys("Jannat");

        // Last Name
        driver.findElement(By.id("last_name")).sendKeys("Promy");

        // Generate a random number between 1000 and 9999 for a shorter email
        int randomNum = (int) (Math.random() * 9000) + 1000;
        String randomEmail = "promy" + randomNum + "@example.com";
        // Input the dynamic email
        driver.findElement(By.id("user_email")).sendKeys(randomEmail);

        // Gender (Radio Button)
        driver.findElement(By.id("radio_1665627729_Female")).click();

        Thread.sleep(2000);
        Utils.scroll(driver, 300);

        // User Password
        driver.findElement(By.id("user_pass")).sendKeys("Te$tP@ssw0rd123");

        // Select Date of Birth from the calendar

        // Open the date picker by clicking on the Date of Birth field
        WebElement dob = driver.findElement(By.xpath("//input[@data-label='Date of Birth']"));
        dob.click();

        // Loop to navigate to the year 1998
        while (true) {
            WebElement currentYear = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));  // Find the current year input field
            int year = Integer.parseInt(currentYear.getAttribute("value"));  // Get the current year displayed in the picker

            if (year == 1998) {
                break;  // Stop the loop when the year is 1998
            } else if (year > 1998) {
                WebElement arrowDown = driver.findElement(By.className("arrowDown"));  // Locate the 'arrowDown' button to go to previous years
                arrowDown.click();  // Click to go to earlier years
            } else {
                WebElement arrowUp = driver.findElement(By.className("arrowUp"));  // Locate the 'arrowUp' button to move forward in years (unlikely needed here)
                arrowUp.click();  // Click to move to later years
            }
        }

        // Add a small wait to ensure the year is updated before proceeding
        Thread.sleep(500);


        // Select the month August using the dropdown
        Select monthDropdown = new Select(driver.findElement(By.xpath("//select[@class='flatpickr-monthDropdown-months']")));
        monthDropdown.selectByVisibleText("August");  // Selects August from the dropdown

        // Select the day 21 in August 1998
        WebElement selectDay = driver.findElement(By.xpath("//span[@aria-label='August 21, 1998']"));
        selectDay.click();

        // Nationality
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");

        // Phone - Locate by name attribute and input formatted number
        driver.findElement(By.name("phone_1665627880")).sendKeys("(012) 345-6789");

        // Country (Dropdown)
        WebElement countryDropdown = driver.findElement(By.id("country_1665629257"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("Bangladesh");

        // Scroll down to make sure the dropdown and checkbox are visible
        Utils.scroll(driver, 700);

        // Step 3: Terms and Conditions (Checkbox)
        driver.findElement(By.id("privacy_policy_1665633140")).click();

        // Step 4: Submit the form
        // Use the form's submit button directly
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();

        // Wait for the form to process
        Thread.sleep(3000);

        // Step 5: Assert that the success message is displayed after form submission
        WebElement successMessage = driver.findElement(By.xpath("//ul[contains(text(),'User successfully registered.')]"));
        String actualMessage = successMessage.getText();
        String expectedMessage = "successfully";

        // Verify the success message using assertTrue
        Assertions.assertTrue(actualMessage.contains(expectedMessage), "Form submission message does not match!");


    }

    @AfterAll
    public void finishTest() {
        driver.quit();
    }
}
