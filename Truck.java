/**
 * Implementation of the Truck class, which implements both the Vehicle and TruckVehicle interfaces.
 */
public class Truck implements Vehicle, TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    /**
     * Constructor for the Truck class.
     * @param make the manufacturer of the truck.
     * @param model the model of the truck.
     * @param year the year of manufacture of the truck.
     */
    public Truck(String make, String model, int year) {
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
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public String toString() {
        return String.format("Truck [Make: %s, Model: %s, Year: %d, Capacity: %.2f tons, Transmission: %s]",
                make, model, year, cargoCapacity, transmissionType);
    }
}
