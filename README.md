
How to run the application
--------------------------

- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.
- Use command `mvn test` to run junit test.

How to Run:
============

mvn spring-boot:run    (from employeeservImplementation)


How to test :
=============

mvn test   (from employeeservImplementation)


curl -L -s POST 'http://localhost:8080/v1/bfs/employee' -H 'Content-Type: application/json' --data-raw '{
  "first_name": "David",
  "last_name": "Raja",
  "date_of_birth": "2004-02-01",
  "address": {
    "line1": "addd1",
    "line2": "add2",
    "city": "city",
    "state": "state",
    "country": "USA",
    "zip_code": "zip"
  }
}'

Result:
-------

{
  "first_name": "David",
  "last_name": "Raja",
  "date_of_birth": "2004-02-01",
  "address": {
    "line1": "addd1",
    "line2": "add2",
    "city": "city",
    "state": "state",
    "country": "USA",
    "zip_code": "zip"
  }
}

GET : curl -X GET http://localhost:8080/v1/bfs/employees/1

Result:
-------

{
  "first_name": "David",
  "last_name": "Raja",
  "date_of_birth": "2004-02-01",
  "address": {
    "line1": "addd1",
    "line2": "add2",
    "city": "city",
    "state": "state",
    "country": "USA",
    "zip_code": "zip"
  }
}

--------------------------------------------------------------------------------------------------------


