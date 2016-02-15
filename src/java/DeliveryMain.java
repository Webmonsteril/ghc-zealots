
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
	
	public static Integer[] productWeights;
	
	public static HashMap<Cell, Warehouse> warehouses;

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
		for (int i = 0; i < productWeights.length; i++) {
			System.out.println("productWeights[" + i + "] weight: " + productWeights[i]);
		}
		System.out.println("numOfWarehouses = " + numOfWarehouses);
		for (int i = 0; i < numOfWarehouses; i++) {
			System.out.println("Warehouse[" + i + "] location: " + warehouses.get(i).getLocation());
		}
		
		System.out.println("numOfOrders = " + numOfOrders);
		for (int i = 0; i < numOfOrders; i++) {
			System.out.println("Order[" + i + "] location: " + orders.get(i).getDestination());
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
				handleProductWeights(line);
	    	}
	    	
	    	if (lineNo == 3) {
	    		numOfWarehouses = Integer.valueOf(line);
	    		warehouses = new HashMap<Cell, Warehouse>();
	    		lineOfOrders = 4 + 2 * numOfWarehouses;	   
	    		
	    	}
	    		    	
	    	while (lineNo >= 4 && lineNo < lineOfOrders) {
	    		if (lineNo % 2 == 0) {	    			
	    			String location[] = line.split(" ");
					int r = Integer.valueOf(location[0]);
					int c = Integer.valueOf(location[1]);
					warehouses.put(new Cell(r, c), new Warehouse(new Cell(r,c)));
	    		} else {
	    			HashMap<Integer, Integer> warehouseProductTypes = new HashMap<Integer, Integer>();
	    			String[] warehouseProductTypesStr = line.split(" ");
	    			for (int j = 0; j < numOfProductTypes; j++) {
	    				warehouseProductTypes.put(j, Integer.valueOf(warehouseProductTypesStr[j]));
	    			}
					warehouses.get(wareHouseNo).setProducts(warehouseProductTypes);
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
	    			orders.get(orderNo).setDestination(new Cell(r, c));
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

	private static void handleProductWeights(String line) {
		productWeights = new Integer[numOfProductTypes];
		String[] ptw = line.split(" ");
		for (int i = 0; i < ptw.length; i++) {
			productWeights[i] = Integer.valueOf(ptw[i]);
		}
	}
}
