package Delivery;

import java.util.HashMap;

public class Order {

	private Cell cell;
	private int numOfItemsInOrder;
	private HashMap<Integer, Integer> productTypeInOrder;  
	
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
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
