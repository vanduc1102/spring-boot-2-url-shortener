# URL Shortener

## Overview
Produce a "Url Shortener" application like [https://bitly.com/](https://bitly.com/) or [https://www.shorturl.at/](https://www.shorturl.at/) 

When I enter a URL I get a "short" URL

e.g., https://wizeservices.com/careers ⇒ https://localhost:3000/corto

When I browse to a "short" URL, it redirects to the original URL

e.g., https://localhost:3000/corto ⇒ https://wizeservices.com/careers

We have prepared a basic implementation for the application. You can submit urls and get the urls list stored in an in-memory database.

## Requirements

 1. As a User, I want to be able to create shortenUrls and the shortenUrls should redirect to the originalUrls via the application. ( MVP ).
 
2. As a User, I want to know "How many times a shortenUrl has been used?".

3. As a User, I want to have my own shortenCodes if they are available.

    e.g: 
        I want to use `aapl` for `https://apple.com` and `wzl` for `https://wizeline.com`

4. As a Co-worker, Let's discuss to improve the application at any aspect.


## Development

Colone the repository and run the command below:

`./mvnw clean spring-boot:run`

View Application entryPoint: [http://localhost:8080](http://localhost:8080)

View H2 DB: [http://localhost:8080/h2/](http://localhost:8080/h2/)


## Good Luck!




