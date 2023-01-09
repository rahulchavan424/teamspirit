import java.util.Scanner;

public class ShippingProblem {

    public static boolean checkIfVehicleInWarehouse(String vehicleName,String nearestWarehouse){

        return true;
    }

    public static void updateStatus(String vehicle, String value){

        System.out.println(value);
    }

    public static void main(String[] args) {
        // Declare variables
        String vehicleName;
        String cityName;
        String nearestWarehouse;
        int distanceToWarehouse;
        boolean vehicleInWarehouse;

        // Initialize warehouse locations and distances
        String[] warehouseLocations = {"Delhi", "Bangalore", "Kolkata"};
        int[][] distances = {
                {0, 2000, 1000},
                {1600, 0, 1000},
                {800, 1800, 0}
        };


        //scanner for reading input
        Scanner scanner = new Scanner(System.in);

        //vehicle name and city name
        System.out.println("Enter the vehicle name: ");
        vehicleName = scanner.nextLine();
        System.out.println("Enter the city name: ");
        cityName = scanner.nextLine();

        //nearest warehouse and the distance to it
        nearestWarehouse = "";
        distanceToWarehouse = Integer.MAX_VALUE;
        for (int i = 0; i < warehouseLocations.length; i++) {
            int distance = distances[i][cityIndex(warehouseLocations, cityName)];
            if (distance < distanceToWarehouse) {
                nearestWarehouse = warehouseLocations[i];
                distanceToWarehouse = distance;
            }
        }



        // check if vehicle is in the nearest warehouse
        vehicleInWarehouse = checkIfVehicleInWarehouse(vehicleName, nearestWarehouse);

        // update status of vehicle
        if (vehicleInWarehouse) {
            updateStatus(vehicleName, "dispatched");
        } else {
            // find the nearest next warehouse and check if the vehicle is there
            String nextNearestWarehouse = "";
            int distanceToNextNearestWarehouse = Integer.MAX_VALUE;
            for (int i = 0; i < warehouseLocations.length; i++) {
                if (warehouseLocations[i].equals(nearestWarehouse)) {
                    continue;
                }
                int distance = distances[i][cityIndex(warehouseLocations, cityName)];
                if (distance < distanceToNextNearestWarehouse) {
                    nextNearestWarehouse = warehouseLocations[i];
                    distanceToNextNearestWarehouse = distance;
                }
            }
            boolean vehicleInNextNearestWarehouse = checkIfVehicleInWarehouse(vehicleName, nextNearestWarehouse);
            if (vehicleInNextNearestWarehouse) {
                updateStatus(vehicleName, "dispatched");
            } else {
                System.out.println("Error: Vehicle not found in any warehouse.");
            }
        }
    }

    // returns the index of city in the warehouseLocations array
    public static int cityIndex(String[] warehouseLocations, String cityName) {
        for (int i = 0; i < warehouseLocations.length; i++) {
            if (warehouseLocations[i].equals(cityName)) {
                return i;
            }
        }
        return -1;
    }
}