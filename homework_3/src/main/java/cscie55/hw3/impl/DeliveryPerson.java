package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.api.Person;

/**
 * A delivery person can board the Elevator and ride up to a floor.
 * But he can't enter apartment
 */
public class DeliveryPerson extends Person implements Passenger{

    private int destinationFloor;
    private int currentFloor;
    private final int doorKey;

    public DeliveryPerson(String firstName, String lastName, Address address){
        super(firstName, lastName, address);
        currentFloor = 1;
        destinationFloor = Building.UNDEFINED_FLOOR;
        doorKey = address.hashCode();
    }

    @Override
    public int getDestination() {
        return this.destinationFloor;
    }

    @Override
    public void setDestination(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    @Override
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    @Override
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * This method set the state of the DeliveryPerson
     * It sets the arrival floor as the current floor.
     * It sets the destination floor to 1 as they must return to floor 1.
     * @param arrivalFloor
     */
    @Override
    public void arriveOnFloor(int arrivalFloor) {
        this.setCurrentFloor(arrivalFloor);
        this.setDestination(1);
    }

    /**
     * It takes an Apartment as a parameter, and returns a string of floor id and apartment id.
     * @param apt apartment object
     * @return a string of floor id and apartment id.
     */
    public String ringApartment(Apartment apt){
        return "Ringing floor: "+apt.getAddress().getFloorId()+", apt: "+apt.getAddress().getApartmentId();
    }

    public int getDoorKey() {
        return this.doorKey;
    }
}
