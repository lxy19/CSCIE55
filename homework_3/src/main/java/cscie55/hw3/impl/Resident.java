package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.api.Person;
import cscie55.hw3.exception.KeyDoesNotFitException;
import cscie55.hw3.exception.TooManyResidentsException;

public class Resident extends Person implements Passenger {

    private int destinationFloor;
    private int currentFloor;
    private final int doorKey; // this value is derived from hashCode() of address

    /**
     * This class' constructor requires parameters that are passed to the super class.
     * Also, the hashCode from the address object is set in the resident as a 'doorKey'
     *
     * @param firstName
     * @param lastName
     * @param address
     */
    public Resident(String firstName, String lastName, Address address) {
        super(firstName, lastName, address);
        currentFloor = 1;
        destinationFloor = Building.UNDEFINED_FLOOR;
        doorKey = address.hashCode();
    }

    /**
     * @param apartment - the apt to enter
     * @param key - the key code that will be entered
     * @throws KeyDoesNotFitException
     */
    public void enterApartment(Apartment apartment, int key) throws KeyDoesNotFitException {
            if (apartment.getKey() == getDoorKey()) {
                try {
                    apartment.addResident(this);
                    this.setCurrentFloor(apartment.getFloorId());
                } catch (TooManyResidentsException tmr) {
                    // log error
                }
            } else {
                throw new KeyDoesNotFitException("You don't have permission to enter this apartment");
            }
    }

    /**
     * returns an int indicating the floor to which the Resident [as Passenger] wants to reach.
     *
     * @see Passenger#getDestination()
     */
    public int getDestination() {
        return destinationFloor;
    }

    /**
     * @see Passenger#setDestination(int)
     */
    public void setDestination(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * This method sets the state properties of the resident to reflect their arrival
     * It sets the passenger's destination floor to be UNDEFINED_FLOOR.
     * It also sets the destination floor to the passengers's current floor number.
     * @param arrivalFloor: arrives at the destination floor
     */
    @Override
    public void arriveOnFloor(int arrivalFloor) {
        this.setDestination(Building.UNDEFINED_FLOOR);
        this.setCurrentFloor(arrivalFloor);
    }

    public int getDoorKey() {
        return this.doorKey;
    }

}
