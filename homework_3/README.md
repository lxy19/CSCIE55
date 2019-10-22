Homework 3
CSCIE-55
Fall 2019
Xiangye Li

Last Modified: Saturday, 12-Oct-2019 11:20:58 EDT
Implementation
There are several new types of classes:
• Elevator contains following
Private fields:
floors: an array of Floor objects
currentFloor: current floor
direction: an enum that has values UP and DOWN
passengers: a list of passengers that are destined for each floor
LOGGER: log error messages when try catch block throw exceptions
Constant:
CAPACITY: a constant that is max capacity of the elevator 
Constructor:
A constructor with the parameter an array of floors.
Methods:
Method move(): when elevator moves up or down, updates the elevator's state. In this method, unload passengers if they 
reach the destination floor. If direction is up, board the passengers going up one by one until the elevator is full. 
If the direction is down, board the passengers going down one by one until the elevator is full.
Method boardPassenger(Passenger passenger): This methods get destination of passenger and use it to add to passengers 
traveling to that floor. If the elevator is full, throw ElevatorFullException. Then use LOGGER to log the error when try catch 
block catches the exception.
Method unloadPassengers(): remove passengers destined for the floor at this stop
Method getCurrentFloor(): get the current floor
Method setCurrentFloor(int floorNum): set the current floor number
Method toString: returns a string to indicate the number of passengers on board, and the current floor
Method int getNumberPassengers():  calculate and return the number of passengers on board the elevator based on list 
of the passengers.

• Floor
Private fields:
Design consideration for the following three collections of Passengers: To ensure that Passengers on the wait-queues should get service in the order in which they joined the queue, the queues should be represented by FIFO.
residents: Passengers who are residents on the Floor, not waiting for the elevator
upwardBound: Passengers who are waiting for an Elevator going up
downwardBound: Passengers who are waiting for an Elevator going down
apartments: an array of apartments 
Constructor:
A constructor with a parameter id which is the id of the floor
Methods:
Method callElevator(Passenger passenger): Each time add one passenger waiting on the floor for the Elevator assume a passenger
is exactly in one of the collections upwardBound/downwardBound/residents. 
Method getApartment(): checks to ensure requested apartment number exists

• Building
Constants:
UNDEFINED_FLOOR: set the value to be -1
ID: a constant for the building id
TOTAL_NUM_OF_FLOORS: a constant for the total number of floors
Private fields:
elevator: a private field of the elevator object
floors: An array of floors
Constructor:
A no-argument constructor that creates the elevator and an array of floor objects
Methods:
Getters and setters of elevator and floors objects

• Resident
This is a class that extends Person and implements the Passenger class. 
Private fields:
currentFloor: set to 1 by default
destinationFloor: set to Building.UNDEFINED_FLOOR
doorKey: a hashcode value for the address
Constructor
Created a constructor with the same parameters as the super class - super(firstName,lastName,address) and initialize
the private fields.
Methods:
enterApartment: enter the apartment with a door key. It throws an exception if key doesn't fit.
arriveOnFloor: This method sets the state properties of the resident to reflect their arrival.
It sets the passenger's destination floor to be UNDEFINED_FLOOR.
It also sets the destination floor to the passengers's current floor number.

• DeliveryPerson
The DeliveryPerson class extends Person class and implements Passenger
The DeliveryPerson class just like Resident class. But it doesn't have enterApartment() method.
Method:
ringApartment: It takes an Apartment as a parameter, and returns a string of floor id and apartment id.

• ElevatorFullException 
The class handles the exception when the elevator cannot board a passenger as it is full.

• KeyDoesNotFitException
The class handles the exception when the key doesn't fit

• NoSuchApartmentException
The class handles the exception when the apartment can't be found

• TooManyResidentsException
The class handles the exception when the number of residents exceed MAX_RESIDENTS 

To run the program from the jar file:
java -jar li_xiangye_hw3-1.0-SNAPSHOT-tests.jar cscie55.hw3.ElevatorTest
Results:
JUnit version 4.11
..[ERROR] 2019-10-14 23:04:04.584 [main] Elevator - [cscie55.hw3.exception.ElevatorFullException] Cannot board passenger.
........
Time: 0.018

OK (10 tests)

Jar files
li_xiangye_hw3-1.0-SNAPSHOT-tests.jar: executable jar file to run the program
li_xiangye_hw3-1.0-SNAPSHOT.jar: a snap shot version of the jar file
li_xiangye_hw3-1.0-SNAPSHOT-sources.jar: all the java source files
li_xiangye_hw3-1.0-SNAPSHOT-javadoc.jar: generated java docs in the jar file