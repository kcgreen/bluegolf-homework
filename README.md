# bluegolf-homework

## Question 1

A golfer wants to play in a tour championship which requires progressively qualifying in various stages.  Each qualifying tournament allows those who advance to play in a single event at the next stage.  If you fail to qualify in an event, you are allowed to try again in an event at the same stage that has not yet been held. 

### An ERD of the database

Contained in ERD.pdf

### A Golfer object with methods and logic required.  Methods that require a database call can be done with pseudo-code.  You can assume there is a Tournament.getTournaments() method which returns all of the tournaments.

Server-side application handles requests and responses through J2EE Spring Boot components implementing CRUD server interfaces for a Person model.

```
[{"id":"e2201e76-a13b-459b-9602-9bc29a264702","givenname":"Charlie","surname":"Brown","email":"charlie.brown@peanuts.org"},
{"id":"12af1b4d-d45e-4154-b071-b8e5738a8147","givenname":"Sally","surname":"Brown","email":"sally.brown@peanuts.org"},
{"id":"20b9a63f-6499-45f2-b40f-b80398fb7403","givenname":"Peppermint","surname":"Patty","email":"peppermint.patty@peanuts.org"},
{"id":"b870eb62-6eef-4a70-b733-42ae904d7b50","givenname":"Pig","surname":"Pen","email":"pig.pen@peanuts.org"},
{"id":"b3bbe22b-ebbf-4ecd-b1bf-2eb660ff856c","givenname":"Linus","surname":"VanPelt","email":"linus.vanpelt@peanuts.org"},
{"id":"dd5d694e-8897-406c-aa77-faf1eef2f407","givenname":"Linux","surname":"VanPelt","email":"linux.vanpelt@peanuts.org"},
{"id":"a8e9e15b-f1ac-4a63-b187-a538dfd5bce4","givenname":"Lucy","surname":"VanPelt","email":"lucy.vanpelt@peanuts.org"},
{"id":"9bd82fbf-b727-4f3b-b3be-b4d5949f1826","givenname":"Franklin","surname":"X","email":"franklin.x@peanuts.org"},
{"id":"59664a26-d497-4615-9337-816d4838674c","givenname":"Marcy","surname":"X","email":"marcy.x@peanuts.org"}]
```

## Development Server

Run `mvnw clean install && mvnw spring-boot:run -pl person-service` for a dev server.

Http Verb | Endpoint | Payload Data (JSON) | Result
--- | --- | --- | --- |
POST | http://localhost:8080/v1/person/ | {"id":null,<br/>"givenname":"Charlie",<br/>"surname":"Brown",<br/>"email":"charlie.brown@peanuts.org"} |  Create One
GET | http://localhost:8080/v1/person/ | none | Read All
GET | http://localhost:8080/v1/person/{id} | none | Read One
PUT	 | http://localhost:8080/v1/person/{id} | {"id":"e2201e76-a13b-459b-9602-9bc29a264702",<br/>"givenname":"Charlie",<br/>"surname":"Brown",<br/>"email":"charlie.brown@peanuts.org"} |  Update One
DELETE | http://localhost:8080/v1/person/{id} | none | Delete One

Note: The server is configured to accept CORS from web clients at `http://localhost:4200/` only!

## Development Environment

This project created in [eclipse](https://www.eclipse.org/downloads/packages/release/neon/3/eclipse-ide-java-ee-developers) Version: Neon.3 Release (4.6.3) with the [Spring STS](https://marketplace.eclipse.org/content/spring-tools-aka-spring-ide-and-spring-tool-suite).

### Front end.  You can assume the golfer is logged in.  Design a single page website that should allow for the following functionality:
○	If the golfer is registered for an event but has not entered a result, show a prompt to enter a result (Qualified, Did not qualify).  Submitting the result should be done via a client side call which returns a list of event(s) the golfer is now eligible to register for.  Display the events to the golfer.
○	If the golfer is not registered for an event, show the list of events (if any) they are eligible to register for.


Next steps for this application.

* Migrate Spring Boot application to Spring Cloud microservice.
* MySQL for persistent storage.
* Docker container for cloud readiness.
