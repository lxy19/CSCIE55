package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.NoSuchApartmentException;

import java.util.ArrayDeque;

public class Floor {

    private int id;
    private Apartment[] apartments = new Apartment[4];
    //declare and initialize 3 ArrayDeques, one for residents, upwardBound and downwardBound
    private ArrayDeque<Passenger> residents = new ArrayDeque<>();
    private ArrayDeque<Passenger> upwardBound = new ArrayDeque<>();
    private ArrayDeque<Passenger> downwardBound = new ArrayDeque<>();

    /**
     * Constructor
     * @param id the id of this Floor. Note that the Building has a max of 7 FLoors
     * @see Building, Building.TOTAL_NUM_OF_FLOORS
     */
    public Floor(int id) {
        this.id = id;
        // Creates 4 Apartments for this floor (that is, each floor will have 4 Apartments)
        for (int i = 0; i < apartments.length; i++) {
            apartments[i] = new Apartment(i, id);
        }
    }

    /**
     * This method place the Passengers who want to get on the Elevator on this floor.
     * Insert the passenger at the end of this deque to ensure the passengers on the wait-queues are FIFO
     * depends if the passenger go up or down.There is no limit on the number of passengers who can wait.
     *
     * @param p: passenger
     */
    public void callElevator(Passenger p) {
        if (p.getDestination() > p.getCurrentFloor()) {
            this.upwardBound.add(p);
        } else if (p.getDestination() < p.getCurrentFloor()) {
            this.downwardBound.add(p);
        } else {
            this.residents.add(p);
        }
    }

    /**
     * This method returns passengers who are upwardBound(waiting for elevator going up) and
     * downwardBound(waiting for elevator going down). Residents on the floor are not waiting for the elevator.
     * @return the total number of people who have called the Elevator
     */
    public int getPassengersWaiting() {
        return this.upwardBound.size() + this.downwardBound.size();
    }


    public Apartment getApartment(int apartmentNumber) throws NoSuchApartmentException {
        return apartments[apartmentNumber];
    }

    /**
     *
     * @return passengers who are waiting for an Elevator going up
     */
    public ArrayDeque<Passenger> getPassengersGoingUp() {
        return this.upwardBound;
    }

    /**
     *
     * @return passengers who are waiting for an Elevator going down
     */
    public ArrayDeque<Passenger> getDownwardBound() {
        return this.downwardBound;
    }

    /**
     *
     * @return residents on the fllor, not waiting for the elevator
     */
    public ArrayDeque<Passenger> getResidents() {
        return this.residents;
    }

}
