/**
 * Implementation of the Motorcycle class, which implements both the Vehicle and MotorVehicle interfaces.
 */
public class Motorcycle implements Vehicle, MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    /**
     * Constructor for the Motorcycle class.
     * @param make the manufacturer of the motorcycle.
     * @param model the model of the motorcycle.
     * @param year the year of manufacture of the motorcycle.
     */
    public Motorcycle(String make, String model, int year) {
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
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    @Override
    public int getNumWheels() {
        return numWheels;
    }

    @Override
    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }

    @Override
    public String toString() {
        return String.format("Motorcycle [Make: %s, Model: %s, Year: %d, Wheels: %d, Type: %s]",
                make, model, year, numWheels, motorcycleType);
    }
}
