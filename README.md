# introsde-2016-assignment-3-server

 Assignment 03: SOAP Web Services 
 
 Name: Getachew Demissie Abebe
 
 I have done both server and client by myself
 
 URL of server: https://young-lowlands-83639.herokuapp.com/soap/people?wsdl
 
 Complete Assignment. Server: Method#1 to Method#10
 
                *********** PART 1 ***********
                
                                **** Structure of the project ****

** Here are the packages which contains the application:

i. introsde.assignment.dao In this package there is a class called lifecoachDao, the class that will connect our model to the database. The LifeCoachDao is a singleton java instance that contains an EntityManagerFactory, which is configured by the persistence unit "introsde-jpa". This class will be used to create and Entity Manager whenever we need to execute an operation in the Database.The entity manager provides the operations from and to the database, e.g. find objects, persists them, remove objects from the database, etc.

ii. introsde.assignment.model Contains classe that represent our database in the system. One class for each table in the database and also methods that save, get, update and delete data from each table.

iii. introsde.assignment.soap this package is for my service interface. I have People as the name for my endpoint interface

iv. introsde.assignment.endpoint This package contains server implemetation

v. introsde.assignment.client This package contains Client implemetation

vi. introsde.assignment.test unit testing for person and life status

The database for this application is SQLite DB and named lifecoach.sqlite which is located in the root folder of the repositery. I have also Log.xml file in the main repositery which contains all the request and response information.      

                             *********** PART 2 ***********
                   
             *** Implemented Services using JAX-WS ***
             
Method #1: readPersonList() => This method list all the people in the my database.

Method #2: readPerson(Long id) => Displays  all the Personal information plus current health profile of person with id = 52.

Method #3: updatePerson(Person p) => Update the Personal information of the Person identified by {id = 52}.

Method #4: createPerson(Person p) => It create a new Person (personal information not heaalth profile) and return the newly created Person with its assigned id.

Method #5: deletePerson(Long id) => This method  delete the Person created in the above steps from the system.

Method #6: readPersonHistory(Long id, String measureType) => It return the list of values (the history) of weight for Person identified by id = 53

Method #7: readMeasureTypes() => Return the list of measures supported by the system.

Method #8: readPersonMeasure(Long id, String measureType, Long mid) => It return the value of weight identified by {mid = 3} for Person identified by id = 52

Method #9: savePersonMeasure(Long id, Measure m) =>It save a new measure object  weight of Person identified by id = 101 and archive the old value in the history

Method #10: updatePersonMeasure(Long id, Measure m) => It update the measure identified with weight and mid = 1, related to the Person identified by id = 201                 
                   
                   *********** PART 3 ***********
                   
                            *** Check WSDL ***
                            
==> The server is deployed on heroku and its url is => https://young-lowlands-83639.herokuapp.com/soap/people?wsdl

==> You can check by opeing it on the web browser.

==> The client has been pushed to git hub by separate repositery. the name of the client repositery is:                                    https://github.com/get-abebe/introsde-2016-assignment-03-client

==> To execute the Client,first clone the repositery to your local machine. Then run ant install and run ant execute.client.             And, finally press enter to see all the outputs.
    
    
       
