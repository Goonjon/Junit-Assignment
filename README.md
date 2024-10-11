# Assignment on Junit
### Project Summary: In this project, I automated two web forms using Selenium and JUnit, focusing on tasks like filling out fields, uploading files, and interacting with dynamic UI elements such as date pickers, dropdowns, and checkboxes. The first form, from Digital Unite, involved basic form submission with file upload and success message verification. The second form, from WP Everest Guest Registration, required more complex interactions, including personal details input, gender selection, dynamic date picking, and country selection. Both tasks were validated by asserting successful submission messages, demonstrating efficient handling of form automation and dynamic inputs.

### Technologies I have used: 
- JDK 17
- IntelliJ Community Edition
- Selenium WebDriver: To automate browser interactions.
- JUnit 5: For structuring and running test cases.
- Gradle: For managing dependencies.
- WebDriverManager: To manage browser drivers.


### How to run?
1. Create a new project in IntelliJ IDEA.
2. Ensure Gradle is installed and sync dependencies.
3. Create two separate classes: one for each website automation.
4. Run the tests using the Gradle command: ```gradle clean test``` Or run the tests manually.
5. View reports at: build/reports/tests/test/index.html.

### Test reports of the two web forms
### Webform Learner
<img width="960" alt="Screenshot 2024-10-10 224225" src="https://github.com/user-attachments/assets/9a8611d3-16d2-4045-a0e8-7ae8ccc77203">

### Guest Registration Form
<img width="960" alt="Screenshot 2024-10-10 224428" src="https://github.com/user-attachments/assets/0d66b461-59b4-490e-9263-633cde717b34">


