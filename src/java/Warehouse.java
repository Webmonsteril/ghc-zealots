import java.util.HashMap;

public class Warehouse {

    private Cell location;
    /** product type to quantity */
    private HashMap<Integer, Integer> productsMap = new HashMap<Integer, Integer>();

    public Warehouse(Cell location, HashMap<Integer, Integer> productsMap) {
        this.location = location;
        this.productsMap = productsMap;
    }

    public Warehouse(Cell cell) {
        location = cell;
    }

    public HashMap<Integer, Integer> getProductsMap() {
		return productsMap;
	}

    public void setProducts(HashMap<Integer, Integer> products) {
		this.productsMap = productsMap;
	}

    public Cell getLocation() {
        return location;
    }

    public void setLocation(Cell location) {
        this.location = location;
    }
}
