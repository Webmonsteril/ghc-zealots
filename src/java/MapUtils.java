import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yoel on 2016-02-16.
 */
public class MapUtils {

    public static <K> void incrementValue(Map<K, Integer> map, K key){
        if (map == null){
            map = new HashMap<>();
        }
        if (map.containsKey(key)){
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }

    public static <K> void decrementValue(Map<K, Integer> map, K key){
        if (map == null){
            map = new HashMap<>();
        }
        if (map.containsKey(key)){
            if (map.get(key).equals(1)){
                map.remove(key);
            } else {
                map.put(key, map.get(key) - 1);
            }
        }
    }
}
