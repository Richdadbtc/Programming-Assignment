/**
 * Interface specifically for trucks, defining methods for cargo capacity and transmission type.
 */
public interface TruckVehicle {
    /**
     * Sets the cargo capacity (in tons) for the truck.
     * @param cargoCapacity the cargo capacity to set.
     */
    void setCargoCapacity(double cargoCapacity);

    /**
     * @return the cargo capacity (in tons) of the truck.
     */
    double getCargoCapacity();

    /**
     * Sets the transmission type (manual or automatic) for the truck.
     * @param transmissionType the transmission type to set.
     */
    void setTransmissionType(String transmissionType);

    /**
     * @return the transmission type of the truck.
     */
    String getTransmissionType();
}
