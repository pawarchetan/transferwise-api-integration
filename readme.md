Transferwise api integration

### Technologies used :
- Java8
- Spring Boot 
- Spring Data JPA
- Spring MVC Test
- Swagger
- Junit
- Lombok
- In-memory database (H2)
- Log4j2
- Gradle 4.10.2
- Docker
- IDE used --> IntelliJ

### How to run :
#### 1. Run using command / terminal
- If you are running service from IDE, please install Lombok plugin and enable annotation processing in annotation processor settings 
(Intellij).
- Please add `transferWise.api.key` , `transferWise.api.profile.id` in **application.properties** file.
- Start application using command.
`./gradlew bootRun`
- Access H2 database : `http://localhost:8080/h2-console`
- Access swagger UI to test endpoints : `http://localhost:8080/swagger-ui.html`
- Swagger API documentation is also accessible via : `http://localhost:8080/v2/api-docs`
#### 2. Build and Run docker image
- Please add transferWise.api.key , transferWise.api.profile.id in application.properties file
- Run below commands : 
- `./gradlew clean`
- `./gradlew build docker`
- `docker run -p 8080:8080 -t com.wevat/wevat-api`
- Access H2 database : `http://localhost:8080/h2-console`
- Access swagger UI to test endpoints : `http://localhost:8080/swagger-ui.html`
- Swagger API documentation is also accessible via : `http://localhost:8080/v2/api-docs`

### Code coverage : 
- 90% code has been covered.
- Test has been written using JUnit, Mockito and Spring MVC test (to test controllers).
- Code coverage is measured using Intellij IDEA code coverage plugin. 

### Implementation Details :
- There are two main entities / tables :
    -  **User :**
        - Primary key is generated  using UUID.
        - We have data.sql file which runs every time when we start application and it inserts user details to db.
        - We have feasibility to get all quotes created for a user.
    - **Quote :**
        - Primary key is generated  using UUID.
        - It stores ID and transferWiseQuoteId as a different parameters. The intention behind that one was We might need some parameters
         in future to distinguish between TransferWise quote and Wevat quote.
        - Table name is given as 'Quote' because for now we are storing only quote information. If required we can change it to 
         'payment_log'.
- Apart from requested endpoint (create quote endpoint), we have exposed some endpoints to perform data management / administration on 
entities.**(CRUD operations)**
- **Setters and Getters** are generated using **Lombok** to reduce code.
- Logging has been handled by integration of **Lombok and Log4j2.**
- To test API and to document API, we have integrated Swagger. Swagger is accessible via : **http://localhost:8080/swagger-ui.html**.
- We have implemented custom HTTP request interceptor **"TransferWiseRequestInterceptor.java"** to add API Key and headers automatically 
whenever we are calling TransferWise API.
- We have implemented **"AbstractBaseEntity.java"** class which is responsible for generating the primary key for all entities that we have.
- Spring data JPA has been used to deal with database and transactions.
- RestTemplate has been used to call third party API.
- All amounts are wrapped under **BigDecimal** to have better handling over rounding issues.(Right now we are not doing any calculations but 
in future other data types may create some problems so to avoid it, in advance we have used BigDecimal)
- Implemented **DefaultControllerAdvice** to handle exceptions in more better way.
- Unit testing has been done using **JUnit, Mockito and Spring MVC test**. Spring MVC test has been used for controller (RestController) 
testing.
- Currently logging level is set to Debug to see what request we are sending and what response we receiving.(**logging.level.com.wevat=DEBUG**)

### Future scope : 
- Enable JPA Auditing
- Implementation of Circuit breaker design pattern for dependent external/internal services like TransferWise.
- Integration Tests.
- Integration of code coverage tools like Jacoco and Implementation of BDD using cucumber.
