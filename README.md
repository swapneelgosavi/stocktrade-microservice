## Spring Mixroservice Demo Project - User and its shares crud operation
#### (using  : Spring Boot, Spring REST, Spring JPA, Spring HATEOAS, Spring Validation, Spring Actuator, Swagger )

This is RESTful webservice for crud operations on user and user's shares data. Project provides functionality to add, delete and get data of Users and Shares.
It has features like :
  1. Basic Authentication with Spring Security
  2. Exception Handling- Generic exception for bad requests/errors
  3. Validation for RESTful Services
  4. Dynamic filtering on response data
  5. HATEOAS for RESTful Services
  6. Auto Generation of Swagger Documentation
  7. Monitoring APIs using Spring Boot Actuator

#### Example Requests :

1. Get All Users (GET)
   http://localhost:8080/users
   
2. Get user based on id (GET)
   http://localhost:8080/users/111
   
3. Delete User based on id (DELETE)   
   http://localhost:8080/users/111
   
4. Add New User (POST)
   http://localhost:8080/users/adduser
   
   POST Body : 
    {        
        "name": "ljasda"        
    }
    
    Header : Content-Type : application/json

5. Get User based on id ## HATEOAS example  
    http://localhost:8080/hateoas/users/{id}   
