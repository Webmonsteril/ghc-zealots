package Delivery; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DeliveryMain {

	public static int numOfRows;
	public static int numOfCols;
	public static int numOfDrones;
	public static int numOfTurns;
	public static int maxPayload;
	public static int numOfProductTypes;
	public static int numOfWarehouses;
	public static int numOfOrders;
	
	public static ProductType[] productTypes;
	
	public static HashMap<Integer, Warehouse> wareHouses; 

	public static HashMap<Integer, Order> orders; 
		
	public static void main(String[] args) throws IOException {
		
		handleInputFile(args[0]); 		
		printResults();		
		
	}

	private static void printResults() {
		System.out.println("numOfRows = " + numOfRows);
		System.out.println("numOfCols = " + numOfCols);
		System.out.println("numOfDrones = " + numOfDrones);
		System.out.println("numOfTurns = " + numOfTurns);
		System.out.println("maxPayload = " + maxPayload);
		System.out.println("numOfProductTypes = " + numOfProductTypes);
		for (int i = 0; i < productTypes.length; i++) {
			System.out.println("productType[" + i + "] weight: " + productTypes[i].getWeight());
		}
		System.out.println("numOfWarehouses = " + numOfWarehouses);
		for (int i = 0; i < numOfWarehouses; i++) {
			System.out.println("Warehouse[" + i + "] location: " + wareHouses.get(i).getCell().getR() + " , " +  wareHouses.get(i).getCell().getC());
		}
		
		System.out.println("numOfOrders = " + numOfOrders);
		for (int i = 0; i < numOfOrders; i++) {
			System.out.println("Order[" + i + "] location: " + orders.get(i).getCell().getR() + " , " +  orders.get(i).getCell().getC());
		}
	}	
	
	public static void handleInputFile(String fileLocation) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (fileLocation));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    int lineNo = 0;
	    int lineOfOrders = -1;
	    int wareHouseNo = 0;
	    int orderNo = 0;
	    	    
	    while( ( line = reader.readLine() ) != null ) {
	    	if (lineNo == 0) {
	    		String[] firstLineItems = line.split(" ");
	    		numOfRows = Integer.valueOf(firstLineItems[0]);
	    		numOfCols = Integer.valueOf(firstLineItems[1]);
	    		numOfDrones = Integer.valueOf(firstLineItems[2]);
	    		numOfTurns = Integer.valueOf(firstLineItems[3]);
	    		maxPayload = Integer.valueOf(firstLineItems[4]);	    		
	    	}
	    	
	    	if (lineNo == 1) {
	    		numOfProductTypes = Integer.valueOf(line);	    		
	    	}
	    	
	    	if (lineNo == 2) {
	    		handleProductTypes(line);
	    	}
	    	
	    	if (lineNo == 3) {
	    		numOfWarehouses = Integer.valueOf(line);
	    		wareHouses = new HashMap<Integer, Warehouse>();
	    		lineOfOrders = 4 + 2 * numOfWarehouses;	   
	    		
	    	}
	    		    	
	    	while (lineNo >= 4 && lineNo < lineOfOrders) {
	    		if (lineNo % 2 == 0) {	    			
	    			wareHouses.put(wareHouseNo, new Warehouse());
	    			String location[] = line.split(" ");
	    			int r = Integer.valueOf(location[0]);
	    			int c = Integer.valueOf(location[1]);
	    			wareHouses.get(wareHouseNo).setCell(new Cell(r, c));
	    		} else {
	    			HashMap<Integer, Integer> warehouseProductTypes = new HashMap<Integer, Integer>(); 
	    			String[] warehouseProductTypesStr = line.split(" ");
	    			for (int j = 0; j < numOfProductTypes; j++) {
	    				warehouseProductTypes.put(j, Integer.valueOf(warehouseProductTypesStr[j]));
	    			}
					wareHouses.get(wareHouseNo).setNumPerProductType(warehouseProductTypes);
	    			wareHouseNo ++;
	    				    			
	    		}
	    		lineNo++;	    		
	    		line = reader.readLine();
	    	}
	    	
	    	if (lineNo == lineOfOrders) {
	    		numOfOrders = Integer.valueOf(line);	
	    		orders = new HashMap<Integer, Order>();
	    	}
	    	
	    	if (lineOfOrders > 0 && lineNo > lineOfOrders) {
	    		if ((lineNo - lineOfOrders - 1)  % 3 == 0) {	    			
	    			orders.put(orderNo, new Order());
	    			String location[] = line.split(" ");
	    			int r = Integer.valueOf(location[0]);
	    			int c = Integer.valueOf(location[1]);
	    			orders.get(orderNo).setCell(new Cell(r, c));
	    		} else if ((lineNo - lineOfOrders - 1) % 3 == 1) {
	    			orders.get(orderNo).setNumOfItemsInOrder(Integer.valueOf(line));	    				    			
	    		} else { 
	    			String[] orderProductTypesStr = line.split(" ");
	    			HashMap<Integer, Integer> productTypeInOrder = new HashMap<Integer, Integer>();
	    			for (int j = 0; j < orders.get(orderNo).getNumOfItemsInOrder(); j++) {
	    				productTypeInOrder.put(j, Integer.valueOf(orderProductTypesStr[j]));
	    			}
					orders.get(orderNo).setProductTypeInOrder(productTypeInOrder);	    							
	    			orderNo++;
	    		}	    		
	    	}
	    		    	
	    	lineNo++;
	    }
	}



	private static void handleProductTypes(String line) {
		productTypes = new ProductType[numOfProductTypes];
		String[] ptw = line.split(" ");
		for (int i = 0; i < ptw.length; i++) {
			productTypes[i] = new ProductType();
			productTypes[i].setWeight(Integer.valueOf(ptw[i]));
		}		
	}
	
	
}
