/**
 * Implementation of the Car class, which implements both the Vehicle and CarVehicle interfaces.
 */
public class Car implements Vehicle, CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

    /**
     * Constructor for the Car class.
     * @param make the manufacturer of the car.
     * @param model the model of the car.
     * @param year the year of manufacture of the car.
     */
    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public String toString() {
        return String.format("Car [Make: %s, Model: %s, Year: %d, Doors: %d, Fuel: %s]",
                make, model, year, numDoors, fuelType);
    }
}
