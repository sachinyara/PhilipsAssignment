# PhilipsAssignment
Philips assignment to build a POC on healthcare system.

1. Instruction to run this app:

Please run demo.healthcare.config.Application class to boot this application.

2. Sample resource paths:


Institution Resource
--------------------
POST http://localhost:8082/healthcare-demo/institution/ 
Request Body: {"name":"Vydehi Hospital","description":"Vydehi hospital."}
GET  http://localhost:8082/healthcare-demo/institution/1
DELETE  http://localhost:8082/healthcare-demo/institution/1


Patient Resource
----------------
POST http://localhost:8082/healthcare-demo/patient/ 
Request Body: {"name":"Sachin Kumar","dateOfBirth":"1984-06-18T00:00:00","gender":"M","institution":{"id":1,"name":"Vydehi Hospital","description":"Vydehi hospital."}}
GET  http://localhost:8082/healthcare-demo/patient/1
DELETE  http://localhost:8082/healthcare-demo/patient/1
GET http://localhost:8082/healthcare-demo/patient/1/age

Examination Resource
---------------------
POST http://localhost:8082/healthcare-demo/examination/
Request Body: {"examDate":"2017-06-08T00:00:00","name":"Blood Test","description":"Blood Test","weight":68.0,"height":1.7,"bmi":23.529411,"patient":{"id":1,"name":"Sachin Kumar","dateOfBirth":"1984-06-18T00:00:00","gender":"M","institution":{"id":1,"name":"Vydehi Hospital","description":"Vydehi hospital."}}}
GET http://localhost:8082/healthcare-demo/examination/1
DELETE POST http://localhost:8082/healthcare-demo/examination/1