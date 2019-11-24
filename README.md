# Punch Clock
A Rest API to manage employee's clocks

To run tests please turn **spring.profiles.active** to **test** in application.properties first

To startup application with preloaded data turn **spring.profiles.active** to **dev** in application.properties
and **spring.jpa.hibernate.ddl-auto** to **create** in application-dev.properties. If you want persist data on 
restart server turn **spring.jpa.hibernate.ddl-auto** to **none**
Besides it's necessary have a database called punch_clock in a MySQL instance

To run application with production mode turn **spring.profiles.active** to **prod** in application.properties
Besides it's necessary have a database called punch_clock_prod in a MySQL instance

A admin user will be available with username=**admin** and password=**admin**

### API Example Usage

####  Create a employee with id "06982236360" (only admin)
    curl -X POST http://localhost:8080/employees -d '{"id":"06982236360", "name":"Marcos", "password":"123456"}' -H "Content-Type: application/json" -u admin:admin

####  Get current work day of logged employee
    curl -X GET http://localhost:8080/employee-work-days -u 06982236360:123456

####  Get current work day of employee by id (only admin)
    curl -X GET http://localhost:8080/employee-work-days/06982236360 -u admin:admin

####  Get work day employee of defined date by id (only admin)
    curl -X GET http://localhost:8080/employee-work-days/06982236360?dateInMilliseconds=  -u admin:admin

####  Get current month work of logged employee
    curl -X GET http://localhost:8080/employee-work-days/get-by-month -u 06982236360:123456

####  Get current month work of employee by id (only admin)
    curl -X GET http://localhost:8080/employee-work-days/get-by-month/06982236360 -u admin:admin

####  Get work day employee of defined date by id (only admin)
    curl -X GET "http://localhost:8080/employee-work-days/get-by-month/06982236360?month=11&year=2019" -u admin:admin

####  Punch clock with logged user
    curl -X POST http://localhost:8080/punch-clocks -u 06982236360:123456
