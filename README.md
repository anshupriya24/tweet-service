# tweet-user-service

Tweet service project provides APIs for clients to create and view tweets of their own or others. Microservice architecture principle is used while implementing this REST API, with single responsibility of collaborating using tweets. It depends on Login API for token validation. Once authenticated, clients can access their tweets, request for feed of tweets of other users in platform and clients can also create new tweets. 

## Technical Stack 

- Java 11+
- Spring Boot 2.6.1
- MongoDB Atlas (SaaS)

## Build the Project 
```
mvn clean install 
```

## Run the Project 
```
mvn spring-boot:run
```

Note: Service by default uses 8080 port

## Run the Project with properties externalized 
```
java -Dapp.db.user=devaplnuser -Dapp.db.pwd=<password> -Dsecret.key=<secret_value> -Dvalidate.token.url=http://<AWS_HOST_IP>:<LOGIN_API_PORT>/api/v1/validate-token -jar tweet.service-0.0.1-SNAPSHOT.jar
```

## APIs provided
- `GET /api/v1/tweets/{username}` - Retrieves list of Tweets for provided username. 
- `POST /api/v1/tweets` - Creates a new Tweet of maximum 160 character length. 
- `GET /api/v1/tweets/others/{username}` - Retrieves list of Tweets of users other than {username}. 
- `POST /api/v1/tweets/others` - Retrieves list of Tweets of users other than {username}. Pagination is implemented here

Note: All above endpoints are secured via Bearer token. Secured endpoints expects valid Bearer token in Authorization header. SecurityFilter validates the token before allowing access to target business operation.
