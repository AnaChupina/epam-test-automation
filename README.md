# epm-atlab-java

# EPAM Training Center - Intern Project

## Test Automation Framework for Swag Labs & Swagger Petstore API

Welcome to the EPAM Training Center intern project repository, focusing on automated testing for Swag Labs and the Swagger Petstore API. This project is part of my internship program and aims to showcase my skills in automated testing.

## Project Overview

* **UI Testing for Swag Labs**: I have conducted comprehensive UI end-to-end testing to ensure a seamless user experience within Swag Labs applications.
* **API Testing for Swagger Petstore**: My project includes robust automated testing for the Swagger Petstore API. I've utilized tools like RestAssured and Postman to automate tests and have mastered various authentication methods, including API keys and OAuth 2.0.
* **Report Portal Integration**: To enhance my testing capabilities, I've integrated Report Portal for advanced reporting and logging. This integration provides me with in-depth insights into test executions.

## Key Contributions

In the context of my EPAM Training Center intern project, I have made the following key contributions:

* Developed UI end-to-end tests to ensure a seamless user experience for Swag Labs applications.
* Demonstrated proficiency in API testing, including RestAssured and Postman, with a focus on various authentication methods.
* Established a scalable Test Automation Framework, showcasing my ability to design efficient testing architectures, including the utilization of design patterns.
* Integrated effective logging through Report Portal, enabling comprehensive reporting and analysis.
* Implemented screenshot capture and storage to facilitate issue identification on Report Portal.

## Design Patterns Implemented

1. Singleton Pattern for WebDriver
2. Page Object Pattern
3. Factory Pattern
4. Builder Pattern for DTO
5. Chain of Invocation

## Test Automation Framework Layers
In this project, the Test Automation Framework follows a layered architecture for better organization and separation of concerns. Here are the key layers of the framework:

* **Test Layer** (Test Cases):
This layer comprises the actual test cases or test scripts that define specific test scenarios.
* **Business-Logic Layer** (Model):
The business-logic layer contains models and business objects that represent data and operations related to the application under test.
* **Page Object Layer**:
The Page Object layer encapsulates web pages' elements and actions, promoting reusability and maintainability of UI interactions.
* **Utils Layer**:
The Utils layer contains utility functions and helper classes that assist in various testing tasks.
* **Configuration Layer**:
This layer centralizes all configuration settings, including credentials and sensitive data, typically stored in configuration files or environment variables.
* **Logging and Reporting Layer**:
The Logging and Reporting layer handles the generation of logs and reports, ensuring comprehensive insights into test executions.
* **Test Data Layer**:
Test data, including input data and expected outcomes, is managed within the Test Data layer.
* **Service Handling Layer**:
The Service Handling layer interacts with APIs and manages service-related operations.

## Prerequisites

Before running the tests, ensure that you have the following software installed on your machine:

- [ ] Java (version 17 or higher)
- [ ] Gradle (version 1.0.0 or higher)
- [ ] Selenium WebDriver (version 4.9.1 or higher)
- [ ] Junit (version 5.10.0-M1 or higher)
- [ ] Rest-Assured (version 5.3.0 or higher)

## Reporting

After running the tests, a detailed report will be generated in the target/surefire-reports directory. This report includes information on the test results, including any failures or errors.

