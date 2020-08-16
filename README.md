## Spring Mixroservice Project - User and its shares crud operation
#### (using  : Spring Boot, Spring REST, Spring JPA, Spring HATEOAS, Spring Validation, Spring Actuator, Swagger, H2 database )

This is RESTful webservice for crud operations on user and user's shares data. Project provides functionality to add, delete and get data of Users and Shares.
It has features like :
  1. Basic **Authentication** with Spring Security
  2. **Exception Handling**- Generic exception for bad requests/errors
  3. **Validation** for RESTful Services
  4. **Dynamic filtering** on response data
  5. **HATEOAS** for RESTful Services
  6. Auto Generation of **Swagger** Documentation
  7. Monitoring APIs using Spring Boot **Actuator**

#### Example Requests :
  Note : For Spring security, uncomment Spring security dependecy in pom.xml and send all the request using 
  Basic Auth in header with username: swapneel and password: swapneel

1. Get All Users (GET)<br /> http://localhost:8080/users
   
2. Get user based on id (GET) <br /> http://localhost:8080/users/111
   
3. Delete User based on id (DELETE) <br /> http://localhost:8080/users/111
   
4. Add New User (POST)  <br /> http://localhost:8080/users/adduser
   
   <br />
    POST Body : 
    {        
        "name": "swap"        
    }
    
    <br />
    Note: Validation : Name should be min 2 character    
    <br />
    Header : Content-Type : application/json
    <br />
5. Get User based on id ## HATEOAS example   <br /> http://localhost:8080/hateoas/users/{id}   


<br >
------------------------Share's Data-------------------------------<br />

6. Get All shares (GET) <br /> http://localhost:8080/user/share

7. Get share data for perticular user (GET) <br /> http://localhost:8080/user/111/share

8. Add Share to User (POST) <br /> http://localhost:8080/user/111/addshare
  <br />
  
    POST Body :
     {     
          "price": 1500.0,
          "quantity": 12,
          "scriptName": "SYN",
          "tradeDate": "2020-08-14T18:30:00.000+00:00"      
     }
   
   <br />
   
   Note: Validation :  trade date should not be past date. Script name should be min 3 character
   
   <br />
   Header : Content-Type : application/json
   
  <br />
  
  ### Actuator Link <br />
    http://localhost:8080/actuator
    http://localhost:8080/
  
  ### Swagger UI <br />
    http://localhost:8080/swagger-ui/index.html







