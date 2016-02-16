import java.util.*;

public class Algorithm{
    private Board board;

    public Algorithm(Board board){
        this.board = board;
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

                    int productsWeight = DeliveryMain.productWeights[productType] *
                            productsMap.get(productType);


                    do {

                    } while (true);
                }
            }

            if (time < board.totalTurns) {
                finishExecutionWithRemainingTurns();
            }
        }
    }

    private HashMap<Cell, HashMap<Integer, Integer>> getDroneTask(Cell bestWarehouseLocation){
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

            closestCustomerProductTypes.forEach(
                    productType -> productWeightList.add(new ProductWeight(productType,
                                                         DeliveryMain.productWeights[productType])));

            Collections.sort(productWeightList);

            for (int i = productWeightList.size() - 1; i >= 0; i--){
                if (currentPayload + productWeightList.get(i).getWeight() > DeliveryMain.maxPayload){
                    continue;
                }
                // Load Drone & remove products from orders in board.
                MapUtils.incrementValue(cellProductsMapForTask, productWeightList.get(i).getType());
                //droneTask.put(closestCustomerLocation, )
            }

        } while(true);

        return droneTask;
    }

    private void finishExecutionWithRemainingTurns() {
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
