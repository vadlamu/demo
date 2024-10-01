# Backend Code Challenge

A self-contained coding challenge built with spring boot.

## Prerequisite

This challenge requires Java 21 to be installed.

## Setup

Clone this repository locally and create a single feature branch that will hold the changes for the following challenges.

## Running the Application

To run the application:

    ./mvnw spring-boot:run

Application runs at:

    http://localhost:8080

H2 In-Memory Database Console is at:

    http://localhost:8080/h2-console

Default Challenge Application Endpoints:

[See Below](#default-challenge-application-endpoints)

## Challenges

Provide support for the following stories in the REST service. 

### Challenge 1

Satisfy the following story:

As a user, I want to save my address information so that I can use it for future orders or shipments.

Acceptance Criteria:

* The user can input street address, apartment number, city, state, and postal code.
* The system must validate that address, city, state, and postal code have a value
* The saved address is associated with the user's account.
* The user can view the saved address in their account settings.

### Challenge 2

Satisfy the following story:

As a user, I want to provide a public profile so that others can view information about me.

Acceptance Criteria:

* The user can input a bio and a nickname
* Bio and nickname are optional
* Only bio and nickname can be viewed by other users


## Submit PR

Use GitHub to submit a PR for your branch.

Feel free to describe your thought processes, decisions, concerns, tooling, testing strategies, etc. 
This can be done in the PR description or in GitHub code comments in the diff.

## Default Challenge Application Endpoints

### POST /authenticate

This operation will provide a JWT

Default User Accounts:

| Username    | Password | Roles |
|-------------|----------|-------|
| AnnaConda   | password | USER  |
| PhilIngwell | password | ADMIN |

```
curl --location 'http://localhost:8080/authenticate' \
  --header 'Content-Type: application/json' \
  --data '{
      "username": "{username}",
      "password": "{password}"
  }'
```

### GET /users

Returns all users for ADMIN only

```
curl --location 'http://localhost:8080/users' \
  --header 'Authorization: Bearer {jwt-from-authenticate}'
```

### GET /users/{userId}

Returns a user

```
curl --location 'http://localhost:8080/users/{userId}>' \
  --header 'Authorization: Bearer {jwt-from-authenticate}'
```

### POST /users

Creates a User

```
curl --location 'http://localhost:8080/users' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer {jwt-from-authenticate}' \
  --data '{
      "firstName": "Willie",
      "lastName": "Maykit",
      "username": "WillieMaykit",
      "password": "password",
      "roles": [{
          "name": "USER"
      }]
  }'
```


### PUT /users/{userId}

Update a user

```
curl --location --request PUT 'http://localhost:8080/users/{userId}' \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer {jwt-from-authenticate}' \
  --data '{
      "id": "{id}"
      "firstName": "{firstName}",
      "lastName": "{lastName}",
      "username": "{username}",
      "password": "{password}",
      "roles": [{
          "name": "ADMIN"
      },{
          "name": "USER"
      }]
  }'
```

### DELETE /users

Delete a user

```
curl --location --request DELETE 'http://localhost:8080/users/{userId}' \
  --header 'Authorization: Bearer {jwt-from-authenticate}'
```