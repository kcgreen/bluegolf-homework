# bluegolf-homework

## Question 1

A golfer wants to play in a tour championship which requires progressively qualifying in various stages.  Each qualifying tournament allows those who advance to play in a single event at the next stage.  If you fail to qualify in an event, you are allowed to try again in an event at the same stage that has not yet been held. 

### Part 1
An ERD of the database is contained in the file ERD.pdf

### Part 2

A Golfer object with methods and logic required.  Methods that require a database call can be done with pseudo-code.  You can assume there is a Tournament.getTournaments() method which returns all of the tournaments.

Server-side application handles requests and responses through J2EE Spring Boot Controller implementing CRUD server interfaces for a Golfer model.

Run `./mvnw spring-boot:run` for a dev server.

Action | Http Verb | Endpoint | Request Parameters (query string) | Response (JSON)
--- | --- | --- | --- |
Get current tour info for {username} golfer. | GET | /golfer/v1/golfer/{username} | none | {
   "tourName" : "Local 1 Name",
   "lastName" : "Brown",
   "tourStatus" : "REGISTERED",
   "tourStage" : "LOCAL",
   "firstName" : "Sally",
   "userName" : "SALLY"
}
Get all tour results for {username} golfer. | GET | /golfer/v1/results/{username} | none | [
   {
      "tourPlace" : "Local 1 Place",
      "tourStage" : "LOCAL",
      "tourStartDate" : "2021-02-04",
      "tourName" : "Local 1 Name",
      "tourStatus" : "QUALIFIED"
   },
...
]
Post current tour result for {username} golfer. | POST | /golfer/v1/result/{username} | ?tourindex=1\&tourresult=QUALIFIED | {
   "tourStage" : "REGIONAL",
   "tourName" : "Regional 1 Name",
   "userName" : "SALLY",
   "tourStatus" : "REGISTERED",
   "lastName" : "Brown",
   "firstName" : "Sally"
}
Get eligible tournaments for {username} golfer. | GET | /golfer/v1/tournaments/{username} | none | [
   {
      "name" : "Regional 1 Name",
      "next" : "13",
      "startDate" : "2021-02-13",
      "place" : "Regional 1 Place",
      "tourStage" : "REGIONAL",
      "index" : "9"
   },
...
]
Get all tournaments. | GET	 | /golfer/v1/tournaments | [
   {
      "index" : "1",
      "name" : "Local 1 Name",
      "tourStage" : "LOCAL",
      "startDate" : "2021-02-04",
      "place" : "Local 1 Place",
      "next" : "9"
   },
...
]

### Part 3
Front end.  You can assume the golfer is logged in.  Design a single page website that should allow for the following functionality:
- If the golfer is registered for an event but has not entered a result, show a prompt to enter a result (Qualified, Did not qualify).  Submitting the result should be done via a client side call which returns a list of event(s) the golfer is now eligible to register for.  Display the events to the golfer.
- If the golfer is not registered for an event, show the list of events (if any) they are eligible to register for.

`http://localhost:8080/`
