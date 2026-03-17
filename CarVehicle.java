/**
 * Interface specifically for cars, defining methods for door count and fuel type.
 */
public interface CarVehicle {
    /**
     * Sets the number of doors for the car.
     * @param numDoors the number of doors to set.
     */
    void setNumDoors(int numDoors);

    /**
     * @return the number of doors of the car.
     */
    int getNumDoors();

    /**
     * Sets the fuel type (petrol, diesel, or electric) for the car.
     * @param fuelType the fuel type to set.
     */
    void setFuelType(String fuelType);

    /**
     * @return the fuel type of the car.
     */
    String getFuelType();
}
