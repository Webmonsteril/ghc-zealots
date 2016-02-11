package Delivery;

import java.util.HashMap;

public class Warehouse {

	private Cell cell;
	private HashMap<Integer, Integer> numPerProductType;

	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	public HashMap<Integer, Integer> getNumPerProductType() {
		return numPerProductType;
	}
	public void setNumPerProductType(HashMap<Integer, Integer> numPerProductType) {
		this.numPerProductType = numPerProductType;
	}
	
}
