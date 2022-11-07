# Ark Assignment - Mark Hammond 

## Build / Deploy

### Docker
 - Make sure you have Docker and Docker compose installed on your local machine
 - https://docs.docker.com/desktop/

### Code
- Clone the git repo at: 

```git clone https://github.com/mhamm84/ark-assignment.git```

### Run the API
- Run the following in the top level directory of the project. This will take a little while on first build

```docker-compose up -d```

This deploys the application on localhost:8080.

To view the logs of the app you can tail them via:

```docker-compose logs -t -f --tail api```

After you're done you can stop and tear the api down with:
```docker-compose down```


### API Documentation

This API uses a contract first approach and uses the OpenAPI Specification.
- https://spec.openapis.org/oas/latest.html
- https://swagger.io/specification/

The specification for this API can be found at:

```src/main/resources/ark-assignment-api.yaml```

To view the API, you can import or copy and paste in the contract to this site:
- https://editor.swagger.io/

### Auth

The API has basic authentication to it which the API is secured against. Below are the users and passwords created for it:
#### Admin
- username: admin
- password adminPass

#### User
- username: user
- password userPass

admin will work with all endpoints and user will not work with /admin/** endpoints

### Postman Tests
An exported Postman collection is available in the repo at:
```src/test/resources/Ark.postman_collection.json```

## Improvements
There are definitely a few things I would have added with more time to make the API more robust.
- OpenID Connect & use of JWT auth tokens 
- Build in a mechanism (using claims in the auth token) to make sure only client data pertaining to the logged in client can be selected in queries
- Improved logging of requests and a requestId to trace API requests from different clients
- Add more test coverage
- .env file to add environment config values in