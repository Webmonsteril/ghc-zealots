import java.util.HashMap;

public class Board {
    // Product type, Product quantity --> for all of the orders (total)
    public static HashMap<Integer, Integer> totalOrderedItems = new HashMap<>();

    // TODO: init arrays
	public int totalTurns;
    public Drone[][] dronesTracking;
    public HashMap<Cell, Warehouse> warehouses;
    public HashMap<Cell,  HashMap<Integer, Integer>> cellOrderedProducts = new HashMap<Cell,  HashMap<Integer, Integer>>();
    public HashMap<Integer, Integer> productTypeOrders;
}