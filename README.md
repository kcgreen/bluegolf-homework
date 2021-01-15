# bluegolf-homework

## Question 1

A golfer wants to play in a tour championship which requires progressively qualifying in various stages.  Each qualifying tournament allows those who advance to play in a single event at the next stage.  If you fail to qualify in an event, you are allowed to try again in an event at the same stage that has not yet been held.

Assumptions:
-	8 local qualifying tournaments, 4 regional qualifiers, 2 national qualifiers, one championship.
    -	Local Qualifiers #1&2 feed into Regional Qualifier #1.  LQ 3&4 -> RQ #2, etc.
    -	Regional qualifiers #1 & #2 feed into National Qualifier #1, RQ #3 & #4 into NQ #2.
-	All tournaments in a particular stage will be completed before the next stage but they will not all be on different days
-	Once a stage is complete, if you did not qualify you will not be eligible to compete again until the following year.

Assignment: Assume you are building a three tiered system that will provide a golfer a list of the events they have played in (with a result), the list of events they are eligible to participate in and, after selecting one of these, a confirmation page that will show the path they would take if they successfully qualified.  After the event, the Golfer will then be able to enter their result (successfully qualified or not) and the system will present the options accordingly (register for the next event, find another qualifier in the same stage).

### Part 1
An ERD of the database is contained in the file ERD.pdf

### Part 2

A Golfer object with methods and logic required.  Methods that require a database call can be done with pseudo-code.  You can assume there is a Tournament.getTournaments() method which returns all of the tournaments.

Server-side application handles requests and responses through J2EE Spring Boot Controller implementing CRUD server interfaces for a Golfer model.

Run `./mvnw spring-boot:run` for a dev server.

Action | Http Verb | Endpoint | Request Params (query string) | Response (JSON)
--- | --- | --- | --- | --- |
Get current tour info for {username} golfer. | GET | /golfer/v1/golfer/{username} | none | {"tourName" : "Local 1 Name", "lastName" : "Brown", "tourStatus" : "REGISTERED", "tourStage" : "LOCAL", "firstName" : "Sally", "userName" : "SALLY"}
Get all tour results for {username} golfer. | GET | /golfer/v1/results/{username} | none | \[{"tourPlace" : "Local 1 Place", "tourStage" : "LOCAL", "tourStartDate" : "2021-02-04", "tourName" : "Local 1 Name", "tourStatus" : "QUALIFIED"}, ...\]
Post current tour result for {username} golfer. | POST | /golfer/v1/result/{username} | tourindex=1 tourresult=QUALIFIED | {"tourStage" : "REGIONAL", "tourName" : "Regional 1 Name", "userName" : "SALLY", "tourStatus" : "REGISTERED", "lastName" : "Brown", "firstName" : "Sally"}
Get eligible tournaments for {username} golfer. | GET | /golfer/v1/tournaments/{username} | none | \[{"name" : "Regional 1 Name", "next" : "13", "startDate" : "2021-02-13", "place" : "Regional 1 Place", "tourStage" : "REGIONAL", "index" : "9"}, ...\]
Get all tournaments. | GET	 | /golfer/v1/tournaments | none | \[{ "index" : "1", "name" : "Local 1 Name", "tourStage" : "LOCAL", "startDate" : "2021-02-04", "place" : "Local 1 Place", "next" : "9"}, ...\]

### Part 3
Front end.  You can assume the golfer is logged in.  Design a single page website that should allow for the following functionality:
- If the golfer is registered for an event but has not entered a result, show a prompt to enter a result (Qualified, Did not qualify).  Submitting the result should be done via a client side call which returns a list of event(s) the golfer is now eligible to register for.  Display the events to the golfer.
- If the golfer is not registered for an event, show the list of events (if any) they are eligible to register for.

Go to `http://localhost:8080/` in a web browser.

User Name | First Name | Last Name
--- | --- | ---
CHARLIE | Charlie | Brown
SALLY | Sally | Brown
PP1 | Peppermint | Patty
PP2 | Pig | Pen
LINUS | Linus | VanPelt
LUCY | Lucy | VanPelt
FRANKLIN | Franklin | X
MARCY | Marcy | X

## Question 2

Build a job/process dashboard which users can use to see the status of jobs that their staff has initiated.  Jobs are stored in memory and have a customer id, description, time started, time finished and status.  Design a thread safe solution that shows the user the full list of jobs along with the status.  New jobs will be added to the list and statuses will be updated without the need for the user to refresh the page and will be in sync for all users looking at the dashboard.  If the server restarts it is OK for the list of jobs to reset.

## Question 3

Many golf organizations use volunteers to assist with live scoring. In order to speed up the process of logging these volunteers in, design a process that allows the organization to generate an email that will be sent to the volunteers with a link to log in.  The link should include a secure token that will identify the user and the tournament and, as long as the token has not expired, log them in so that they are able to start scoring.

Build a class that has two primary functions:
-	Given a user and a tournament, generate an encrypted token which expires in 24 hours.
-	Given a token
    -	Validate that it has not yet expired
    -	If it is still valid, return the user and tournament.
