import java.util.*;

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
        int currentPayload = 0;
        HashMap<Cell, HashMap<Integer, Integer>> droneTask = new HashMap<Cell, HashMap<Integer, Integer>>();

        HashMap<Integer, Integer> warehouseProductsMap = board.warehouses.get(bestWarehouseLocation).getProductsMap();
        Set<Cell> customerLocations = DeliveryMain.customerLocations;
        HashMap<Integer, Integer> closestCustomerProductsMap;
        List<ProductWeight> productWeightList;

        do {
            Cell closestCustomerLocation = CellUtils.getCellWithMinDistance(bestWarehouseLocation, customerLocations);
            closestCustomerProductsMap = board.cellOrderedProducts.get(closestCustomerLocation);
            Set<Integer> closestCustomerProductTypes = closestCustomerProductsMap.keySet();
            productWeightList = new ArrayList<ProductWeight>();

            for (Integer productType : closestCustomerProductTypes){
                productWeightList.add(new ProductWeight(productType,
                        DeliveryMain.productWeights[productType]));
            }

            Collections.sort(productWeightList);

            for (int i = productWeightList.size() - 1; i >= 0; i--){
                ProductWeight currentProduct = productWeightList.get(i);

                if (currentPayload +  currentProduct.getWeight() > DeliveryMain.maxPayload){
                    // current product cannot be loaded (adding product would exceed drone payload).
                    // TODO: make more efficient by trying again from the smallest product (or maybe knapsack)
                    continue;
                }
                currentPayload += currentProduct.getWeight();
                // Load Drone & remove products from orders in board.
                warehouseProductsMap.remove(currentProduct.getType()); //board.warehouses
                closestCustomerProductsMap.remove(currentProduct.getType()); // board.cellOrderedProducts
                MapUtils.decrementValue(board.productTypeOrders, currentProduct.getType());
                MapUtils.incrementValue(droneTask.get(closestCustomerLocation), currentProduct.getType());


                //droneTask.put(closestCustomerLocation, )
            }
            customerLocations.remove(closestCustomerLocation); // finished loading all products for closest customer.

        } while(currentPayload + productWeightList.get(0).getWeight() <= DeliveryMain.maxPayload || !customerLocations.isEmpty());
        //commands.append(DroneUtils.generateDeliveryCommand(drone, orderId, currentProduct.getType(), 1));
        return droneTask;
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
