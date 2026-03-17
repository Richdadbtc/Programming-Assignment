/**
 * The core interface defining the contract for all vehicles in the system.
 */
public interface Vehicle {
    /**
     * @return the make (manufacturer) of the vehicle.
     */
    String getMake();

    /**
     * @return the model of the vehicle.
     */
    String getModel();

    /**
     * @return the year of manufacture of the vehicle.
     */
    int getYear();
}
