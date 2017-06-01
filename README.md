# Application
The purpose of this application is to organize the skills, open positions, and interviews for all potential candidates.  The flow is first you setup a skill and assign questions to that skill.  Then you create a position, and assiocate one or more skills with that position.  Then you create an interview for a specific position.  When an interview is created, all the questions for all the skills will be shown.  The recruiter can then enter the answers that were given during the interview and score their progress.

## Microservices
There are currently 3 microservice applications here.  They are located under the applications folder.  

### Skill-Server
Houses the entire catalog of skills we hire for.  Each skill contains several questions.  The questions are either L1 or L2.  The skills are stored in mongo along with the questions.

### Position-Server
This microservice is responsible for handling the positions.  The combination of company and title should make the position unquiue.  Two companies may have the same position open but may have different requirements for the skills for each position.  The positions are also stored in mongo.

### Interview-Server
This microservice is responsible for managing the interview that are setup.  An interview should be created prior to physically setting up the interview.  Once you get the candidate on the phone (or in person), you can then open up the interview and all the questoins that need to be asked will be displayed.  The recruiter should ask every question and record the candidate's answers into the system.  A score should also be assigned.  The interviews are sotred in MySQL, the answers during the interview will be saved in redis.  Once the interview has been completed, the interview, questions, and answers will be written out to mongo.  Questions/skill may change over time so it is good to have an accurate snapshot of what was asked during the interview.

## UI

### Gateway
The current UI is done in freemarker.  The gateway microservice returns Model/Views for the freemarker templates.  It internally makes calls out to the other microservices.  The UI can be recreated by calling the exposed microservices.

# Build
It is recommended to use Intellij as the IDE based on the project structure.  Gradle is used to build the project and docker is being used to run it.

## Developer Environment
Once you checkout the code, you can setup the backing services with docker-compose.  Simply run the following from the project root:
   
    docker-compose up

This will startup a mongo, redis, MySQL, and Eureka server.

## Build Docker Image
To build the Docker image, simply run the following from the root:

    ./gradlew buildDocker
    
This will build all the microservice projects.  You can also run this command individually from each project if you want more granular control

## Push Docker image
If you want to push docker images to the nexus repo, run the following from the root:

    ./gradlew buildDocker -Ppush
    
NOTE:  The UAT server is listening for changes to the nexus repo.  If you push changes to the repo, they will be deployed out to the UAT server. 
For more information refer to [Using Docker and Docker-Compose][]

## API
You can see the microservices API with swagger.  You can hit the swagger ui by running:

    http://localhost:8080/swagger-ui.html
   
  


[Using Docker and Docker-Compose]: https://jhipster.github.io/documentation-archive/v4.5.1/docker-compose
