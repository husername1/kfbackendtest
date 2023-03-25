# kfbackendtest

Hello, thank you for taking the time to review my code test.

## About

This is a Java 17 Spring Boot program written in IntelliJ IDEA.

I used PostMan to test the API.

Bonus Requirements: It uses `spring-retry` to retry the API if it fails.

## How to run the program

1. Open `app` folder in IntelliJ IDEA.
2. Add an environment variable for the API key. Go to `AppApplication.java`, right click the main method -> `Modify Run Configuration...` and add the variable `apiKey` and set to the api key. I didn't want to commit this.
3. Run `AppApplication` with the main method.
4. Call a POST request to http://localhost:8080/outages with a body of `norwich-pear-tree`. This will do as you've asked in the task.

## How to run the tests

1. In IntelliJ IDEA, right click the `test` folder and select `Run 'Tests in 'app.test''`.
