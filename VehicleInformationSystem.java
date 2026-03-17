import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the Vehicle Information System.
 * This class handles user interaction, object creation, and displaying vehicle details.
 */
public class VehicleInformationSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Vehicle Information System!");
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    createCar();
                    break;
                case 2:
                    createMotorcycle();
                    break;
                case 3:
                    createTruck();
                    break;
                case 4:
                    displayVehicles();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add a Car");
        System.out.println("2. Add a Motorcycle");
        System.out.println("3. Add a Truck");
        System.out.println("4. Display All Vehicles");
        System.out.println("5. Exit");
    }

    private static void createCar() {
        System.out.println("\nEnter Car Details:");
        String make = getStringInput("Make: ");
        String model = getStringInput("Model: ");
        int year = getIntInput("Year of Manufacture: ");
        int doors = getIntInput("Number of Doors: ");
        String fuelType = getStringInput("Fuel Type (petrol, diesel, or electric): ");

        Car car = new Car(make, model, year);
        car.setNumDoors(doors);
        car.setFuelType(fuelType);
        vehicles.add(car);
        System.out.println("Car added successfully!");
    }

    private static void createMotorcycle() {
        System.out.println("\nEnter Motorcycle Details:");
        String make = getStringInput("Make: ");
        String model = getStringInput("Model: ");
        int year = getIntInput("Year of Manufacture: ");
        int wheels = getIntInput("Number of Wheels: ");
        String type = getStringInput("Type (sport, cruiser, or off-road): ");

        Motorcycle motorcycle = new Motorcycle(make, model, year);
        motorcycle.setNumWheels(wheels);
        motorcycle.setMotorcycleType(type);
        vehicles.add(motorcycle);
        System.out.println("Motorcycle added successfully!");
    }

    private static void createTruck() {
        System.out.println("\nEnter Truck Details:");
        String make = getStringInput("Make: ");
        String model = getStringInput("Model: ");
        int year = getIntInput("Year of Manufacture: ");
        double capacity = getDoubleInput("Cargo Capacity (in tons): ");
        String transmission = getStringInput("Transmission Type (manual or automatic): ");

        Truck truck = new Truck(make, model, year);
        truck.setCargoCapacity(capacity);
        truck.setTransmissionType(transmission);
        vehicles.add(truck);
        System.out.println("Truck added successfully!");
    }

    private static void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles recorded yet.");
            return;
        }

        System.out.println("\n--- Recorded Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    // Helper methods for input validation and error handling

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input < 0) throw new NumberFormatException();
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = Double.parseDouble(scanner.nextLine().trim());
                if (input < 0) throw new NumberFormatException();
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive number.");
            }
        }
    }
}
