# kfbackendtest

Hello, thank you for taking the time to review my code test.

This program has 1 `POST` endpoint `/outages` which takes a `siteId`. The program then gets the outages and the site info for the given site id, filters and maps this data into a new list, then posts this processed data to the site-outages endpoint.

## About

This is a Java 17 Spring Boot program written in IntelliJ IDEA.

I used PostMan to test the API.

Bonus Requirements: It uses `spring-retry` to retry the API if it fails.

## How to run the program

1. Open `app` folder in IntelliJ IDEA.
2. Add an environment variable for the API key. Go to `AppApplication.java`, right click the main method -> `Modify Run Configuration...` and add the variable `apiKey` and set to the api key. I didn't want to commit this because that wouldn't be secure. I've asked my recruitment contact to pass on the starter .zip folder I was given, which had the API key in it.
3. Run `AppApplication` with the main method.
4. Call a POST request to http://localhost:8080/outages with a body of `norwich-pear-tree`. This will do as you've asked in the task.

## How to run the tests

1. In IntelliJ IDEA, right click the `test` folder and select `Run 'Tests in 'app.test''`.
