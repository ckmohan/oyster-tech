
Pre-Request: 
    JDK Installed > 1.8
    CURL tool or RestClient (Postman)


Step 1: 
    To run just execute:
    
    mvn spring-boot:run

Step 2: 
    
    Open New Terminal or Command shell and type below GET Request

    curl -X GET http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b
 
    Expected Result :404,"error":"Not Found",Cannot find oyster

Step 3:
    
    Type the POST Command to create Oyster Card with Default amount (£30 Is Hardcoded)

    curl -X POST http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b

    Type the GET Reqest to verify the card is created and balance is £30
    
    curl -X GET http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b
    
Step 4:
    
    Type the POST Command for Bus Travel
    
    curl -X POST http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b/tap/bus
    
    Type the GET Reqest to verify balance should be £28.20
    
    curl -X GET http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b
    
Step 5:
    
    Type the POST Command for TUBE Travel to HALBORN
 
     curl -X POST http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b//tap/tube/HOLBORN
     
     Type the GET Reqest to verify balance should be £25.00 ( Max Fare deducted)
     
     curl -X GET http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b
     
     Type the POST Command for TUBE Travel to WIMBLEDON
     
     curl -X POST http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b//tap/tube/WIMBLEDON
     
     curl -X GET http://localhost:8080/oyster/ba43c4e6-14c1-11e8-b642-0ed5f89f718b
     
     .............
     .............
     .............

Can be definelty improved with couple of Iteration :) :)

Unit Test Report 

    Results :
        Tests run: 10, Failures: 0, Errors: 0, Skipped: 0

- Persist oyster tap time so if the tap happens within a short time, ignore one of them (prevent double tapping)
- Station id is a string, ideally it should be a proper ID (oversimplified)
- Station repository is an enum, it should be a proper Entity with its respective repository and DB table (again, oversimplification for this exercise)
- Better handling of incorrect requests (i.e. return 409 if trying to create a oyster card that already exists, etc.)
- Better handling of incorrect request params (id no being a proper UUID, etc.)
- Integration is REQUIRED