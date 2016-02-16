/**
 * Created by Yoel on 2016-02-16.
 */
public class DroneUtils {

    public static String generateDeliveryCommand(int droneId, int orderID, int productType, int quantity){
        return droneId + " D " + orderID + " " + productType + " " + quantity;
    }
}
