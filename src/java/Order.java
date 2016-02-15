import java.util.HashMap;

public class Order {

	private Cell destination;
	private int numOfItemsInOrder;
	private HashMap<Integer, Integer> productTypeInOrder;  
	
	public Cell getDestination() {
		return destination;
	}
	public void setDestination(Cell destination) {
		this.destination = destination;
	}
	public int getNumOfItemsInOrder() {
		return numOfItemsInOrder;
	}
	public void setNumOfItemsInOrder(int numOfItemsInOrder) {
		this.numOfItemsInOrder = numOfItemsInOrder;
	}
	public HashMap<Integer, Integer> getProductTypeInOrder() {
		return productTypeInOrder;
	}
	public void setProductTypeInOrder(HashMap<Integer, Integer> productTypeInOrder) {
		this.productTypeInOrder = productTypeInOrder;
	}
}
