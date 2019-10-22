package cscie55.hw3.impl;

import cscie55.hw3.api.Passenger;
import cscie55.hw3.exception.ElevatorFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Elevator {

	private int currentFloor = 1;
	private Floor[] floors;
	public final static int CAPACITY = 10;
	private List<List<Passenger>> passengers = new ArrayList<>();

	private static final Logger LOGGER = LogManager.getLogger(Elevator.class.getName());

	private enum direction {UP, DOWN}

	;
	private direction elevatorDirection = direction.UP;

	/**
	 * This constructor has a parameter floors. It initializes the new List<List<Passenger>> object to track passengers on board
	 *
	 * @param floors floors is the array of Floor objects, as in HW 2.
	 */
	public Elevator(Floor[] floors) {
		currentFloor = 1;
		this.floors = floors;
		int n = 0;
		while (n < Building.TOTAL_NUM_OF_FLOORS){
			this.passengers.add(new ArrayList<Passenger>());
			n++;
		}
	}

	/**
	 * When the elevator moves up, dropping passengers along the way.
	 * Unload passengers if they reach the destination floor.
	 * If direction is up, board the passengers going up one by one until the elevator is full.
	 * If the direction is down, board the passengers going down one by one until the elevator is full.
	 */
	public void move() {
		if (this.elevatorDirection == direction.UP) {
			setCurrentFloor(++currentFloor);
			if (getCurrentFloor() == Building.TOTAL_NUM_OF_FLOORS) {
				elevatorDirection = direction.DOWN;
			}
		} else {
			setCurrentFloor(--currentFloor);
			if (getCurrentFloor() == 1) {
				elevatorDirection = direction.UP;
			}
		}

		//unload passengers
		unloadPassengers();

		int floorNum = getCurrentFloor() - 1;
		//If there is passengers waiting, need to board passengers one by one until the elevator is full
		if (elevatorDirection == direction.UP) {
			try {
				while (!this.floors[floorNum].getPassengersGoingUp().isEmpty()) {
					boardPassenger(this.floors[floorNum].getPassengersGoingUp().poll());
				}
				//the passengers boarded successfully
			} catch (ElevatorFullException e) {
				//The passenger was unable to board because the elevator is full
				LOGGER.error("[" + e.getClass().getName() + "] Cannot board passenger.");
			}
		}
		if (elevatorDirection == direction.DOWN) {
			try {
				while (!this.floors[floorNum].getDownwardBound().isEmpty()) {
					boardPassenger(this.floors[floorNum].getDownwardBound().poll());
				}
				//the passengers boarded successfully
			} catch (ElevatorFullException e) {
				//The passenger was unable to board because the elevator is full
				LOGGER.error("[" + e.getClass().getName() + "] Cannot board passenger.");
			}
		}
	}

	/**
	 * This methods get destination of passenger and use it to add to passengers traveling to that floor
	 * Use the LOGGER.error() method to report your error to the log file
	 *
	 * @param passenger
	 * @throws ElevatorFullException if the number of passengers is over 10 on board
	 */
	public void boardPassenger(Passenger passenger) throws ElevatorFullException {
		int destination = passenger.getDestination() - 1;
		this.passengers.get(destination).add(passenger);
		if (this.passengers.get(destination).size() >= Elevator.CAPACITY) {
			throw new ElevatorFullException("The passenger was unable to board because the elevator is full");
		}
	}

	/**
	 * Remove passengers destined for the floor at this stop.
	 * Add resdients to the resident collection
	 */
	private void unloadPassengers() {
		int floorNum = currentFloor - 1;
		for (Passenger passenger : passengers.get(floorNum)) {
			passenger.arriveOnFloor(floorNum);
			floors[floorNum].getResidents().add(passenger);
		}
		passengers.get(floorNum).clear();
	}

	public int getCurrentFloor() {
		return this.currentFloor;
	}

	private void setCurrentFloor(int floorNum) {
		currentFloor = floorNum;
	}

	public String toString() {
		for (List<Passenger> passengersAtFloor : this.passengers)
			for (Passenger passenger : passengersAtFloor) {
				System.out.println(passenger.getCurrentFloor() + " destination " + passenger.getDestination());
			}
		return "Floor " + currentFloor + ": " + getNumberPassengers() + " passengers";
	}

	/**
	 * calculate and return the number of passengers on board the elevator
	 *
	 * @return the number of passengers on board the elevator
	 */
	public int getNumberPassengers() {
		int n = 0;
		int count = 0;
		while (n < Building.TOTAL_NUM_OF_FLOORS) {
			count += passengers.get(n).size();
			n++;
		}
		return count;
	}

}
