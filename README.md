# API Testing with Playwright

A Java-based API testing project that uses Playwright's API client and TestNG to test booking operations against the [Restful Booker demo API](https://restful-booker.herokuapp.com/).

## Tech stack

- Java 17
- Maven
- Playwright Java
- TestNG
- Gson for JSON serialization and parsing

## Test coverage

The regression suite exercises these API operations:

- Authenticate and obtain a token
- Create a booking
- Retrieve the list of bookings and a specific booking
- Update a booking with `PUT`
- Partially update a booking with `PATCH`
- Delete a booking

The tests share the authentication token and booking ID created during the suite.

## Project structure

```text
src/
├── main/java/                  # Application entry point
└── test/java/
    ├── Test/                   # TestNG suite and shared setup
    ├── GET/                    # Booking retrieval
    ├── POST/                   # Authentication and booking creation
    ├── PUT/                    # Full booking update
    ├── PATCH/                  # Partial booking update
    ├── DELETE/                 # Booking deletion
    └── resource/               # Booking and login request models
```

## Prerequisites

- JDK 17 or later
- Maven
- Network access to `restful-booker.herokuapp.com`

## Running the tests

Run these commands from the directory containing `pom.xml`:

```powershell
cd D:\Playwright_practice\Playlab\ApiTesting\apitesting
mvn test "-Dusername=YOUR_USERNAME" "-Dpassword=YOUR_PASSWORD"
```

To run the `Regression` group explicitly:

```powershell
mvn test -PRegression "-Dusername=YOUR_USERNAME" "-Dpassword=YOUR_PASSWORD"
```

The login test reads credentials from the `username` and `password` Java system properties. Replace the placeholders with credentials accepted by the demo API, and do not commit real credentials to the repository.
