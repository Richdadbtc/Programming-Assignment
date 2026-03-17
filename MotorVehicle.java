/**
 * Interface specifically for motorcycles, defining methods for wheel count and type.
 */
public interface MotorVehicle {
    /**
     * Sets the number of wheels for the motorcycle.
     * @param numWheels the number of wheels to set.
     */
    void setNumWheels(int numWheels);

    /**
     * @return the number of wheels of the motorcycle.
     */
    int getNumWheels();

    /**
     * Sets the type of motorcycle (sport, cruiser, or off-road).
     * @param motorcycleType the type of motorcycle to set.
     */
    void setMotorcycleType(String motorcycleType);

    /**
     * @return the type of the motorcycle.
     */
    String getMotorcycleType();
}
