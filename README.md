# URL Shortener

## Overview
Produce a "Url Shortener" service:

like bitly or Google url shortener

when I enter a URL I get a "short" URL

e.g., https://wizeservices.com/careers ⇒ https://localhost:3000/corto

when I browse to a "short" URL, it redirects to the original URL

e.g., https://localhost:3000/corto ⇒ https://wizeservices.com/careers

when I visit some specific URL, I see a list of the short and long URL pairs in the system

## Requirements

1. As a User, I want to have my shortenUrl and the shortenUrl should redirect to the originalUrl ( MVP ).
2. As a User, I want to know "How many times the shortenUrl has been used?".
3. As a User, I want to have my own shortenCode if it is available.

e.g: 
I want to use `aapl` for `https://apple.com` and `wzl` for `https://wizeline.com`

4. As a CoWorker, Let's discuss to improve the application at any aspect.


## Development

Colone the repository and run the command below:

`./mvnw clean spring-boot:run`

View Application entryPoint: http://localhost:8080

View H2 DB: http://localhost:8080/h2/


## Good Luck!




