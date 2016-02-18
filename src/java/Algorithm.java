import java.util.*;
import java.util.stream.Collectors;

public class Algorithm{
    private Board board;
    private StringBuilder commands;

    public Algorithm(Board board){
        this.board = board;
        commands = new StringBuilder("");
    }

    public void solve() {
        int numOfDrones = board.dronesTracking[0].length;
        int currentDrone;
        int time;
        boolean stop = false;
        Cell bestWarehouseLocation;

        for (time = 0; !stop && time < board.totalTurns; time++) {
            for (currentDrone = 0; currentDrone < numOfDrones; currentDrone++) {
                if (!board.dronesTracking[time][currentDrone].isAvailable()) {
                    continue;
                }
                bestWarehouseLocation = chooseBestWarehouseLocation(board.dronesTracking[time][currentDrone]);

                if (bestWarehouseLocation == null) {
                    stop = true;
                    break;
                }
                // Drone is available.
                HashMap<Integer, Integer> productsMap = board.warehouses.get(bestWarehouseLocation).getProductsMap();

                for (Integer productType : productsMap.keySet()) {

                    int productsWeight = DeliveryMain.productWeights[productType] * productsMap.get(productType);


                    do {

                    } while (true);
                }
            }
        }
        
        if (time < board.totalTurns){
            finishExecutionWithTurnsSurplus();
        }
    }

    private HashMap<Cell, HashMap<Integer, Integer>> getDroneTask(Cell bestWarehouseLocation, Drone drone){
        int dronePayload = 0;
        HashMap<Cell, HashMap<Integer, Integer>> droneTask = new HashMap<Cell, HashMap<Integer, Integer>>();
        HashMap<Integer, Integer> warehouseProductsMap = board.warehouses.get(bestWarehouseLocation).getProductsMap();
        Set<Cell> customerLocations = DeliveryMain.customerLocations;
        HashMap<Integer, Integer> closestCustomerProductsMap;
        List<Integer> productsSortedIndicesByWeight;
        Set<Integer> closestCustomerProductTypes;
        Cell closestCustomerLocation;
        int currentWeight = 0;

        do {
            closestCustomerLocation = CellUtils.getCellWithMinDistance(bestWarehouseLocation, customerLocations);

            closestCustomerProductsMap = board.cellOrderedProducts.get(closestCustomerLocation);

            closestCustomerProductTypes = closestCustomerProductsMap.keySet();

            productsSortedIndicesByWeight = getProductSortedIndicesByWeight(closestCustomerLocation);

            for (int productType = productsSortedIndicesByWeight.size()  - 1; productType >= 0; productType--){
                // Note that the product type index is sorted by wight.

                currentWeight = DeliveryMain.productWeights[productType];

                if (dronePayload +  currentWeight > DeliveryMain.maxPayload){
                    // current product cannot be loaded (adding product would exceed drone payload).
                    // TODO: make more efficient by trying again from the smallest product (or maybe knapsack)
                    continue;
                }
                dronePayload += currentWeight;
                // Load Drone & remove products from orders in board.
                warehouseProductsMap.remove(productType); //board.warehouses
                closestCustomerProductsMap.remove(productType); // board.cellOrderedProducts
                MapUtils.decrementValue(board.productTypeOrders, productType);
                MapUtils.incrementValue(droneTask.get(closestCustomerLocation), productType);


                //droneTask.put(closestCustomerLocation, )
            }
            customerLocations.remove(closestCustomerLocation); // finished loading all products for closest customer.

        } while(dronePayload + currentWeight <= DeliveryMain.maxPayload || !customerLocations.isEmpty());
        //commands.append(DroneUtils.generateDeliveryCommand(drone, orderId, currentProduct.getType(), 1));
        return droneTask;
    }

    private List<Integer> getProductSortedIndicesByWeight(Cell closestCustomerLocation) {
        return DeliveryMain.cellOrderedProducts.get(closestCustomerLocation).keySet()
                .stream().sorted(new Comparator<Integer>(){
            public int compare(Integer productType1, Integer productType2){
                return DeliveryMain.productWeights[productType1] - DeliveryMain.productWeights[productType2];
            }}).collect(Collectors.toList());
    }

    private void finishExecutionWithTurnsSurplus() {
        //TODO: wait with drones on remaining turns.
    }

    private Cell chooseBestWarehouseLocation(Drone drone) {
        Cell closestWarehouse;
        Set<Cell> warehouses = DeliveryMain.warehouses.keySet();
        boolean warehouseContainsWantedItems;
        HashMap<Integer, Integer> productsInClosestWarehouse = new HashMap<Integer, Integer>();

        do {
            closestWarehouse = CellUtils.getCellWithMinDistance(drone.getLocation(), warehouses);
            if (closestWarehouse == null){
                break;
            }
            productsInClosestWarehouse = DeliveryMain.warehouses.get(closestWarehouse).getProductsMap();

            warehouseContainsWantedItems = isProductsMapInOrderedProducts(productsInClosestWarehouse);

            warehouses.remove(closestWarehouse);

        } while(!warehouseContainsWantedItems);

        return closestWarehouse;
    }

    private boolean isProductsMapInOrderedProducts(HashMap<Integer, Integer> productsMap){
        if (productsMap == null || productsMap.isEmpty()){
            return false;
        }

        for (Integer productType: productsMap.keySet()){
            if(!board.totalOrderedItems.containsKey(productType)
                    || board.totalOrderedItems.get(productType) == 0){
                return false;
            }
        }

        return true;
    }
}
