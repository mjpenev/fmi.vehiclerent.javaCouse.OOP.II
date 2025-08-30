package bg.sofia.uni.fmi.mjt.vehiclerent;

import bg.sofia.uni.fmi.mjt.vehiclerent.driver.Driver;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.InvalidRentingPeriodException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleAlreadyRentedException;
import bg.sofia.uni.fmi.mjt.vehiclerent.exception.VehicleNotRentedException;
import bg.sofia.uni.fmi.mjt.vehiclerent.vehicle.Vehicle;

import java.time.LocalDateTime;

public class RentalService {

    public void rentVehicle(Driver driver, Vehicle vehicle, LocalDateTime startOfRent) throws VehicleAlreadyRentedException {
        try {
            vehicle.rent(driver, startOfRent);
        }
        catch (IllegalArgumentException e) {
            System.out.println(String.format("Invalid argument. %s", e.getMessage()));
        }
        catch (VehicleAlreadyRentedException e) {
            System.out.println(String.format("Vehicle is already rented. %s", e.getMessage()));
        }
    }

    public double returnVehicle(Vehicle vehicle, LocalDateTime endOfRent) throws IllegalArgumentException, InvalidRentingPeriodException, VehicleNotRentedException {
        double price = 0;
        try {
            vehicle.returnBack(endOfRent);
            price = vehicle.calculateRentalPrice(vehicle.getStartRentTime(), endOfRent);
        }
        catch (InvalidRentingPeriodException e) {
            System.out.println("Period is not valid!");
        }
        catch (VehicleNotRentedException e) {
            System.out.println("Vehicle is not rented!");
        }
        catch (IllegalArgumentException e) {
            System.out.println("There is an illegal argument!");
        }

        return price;
    }
}
