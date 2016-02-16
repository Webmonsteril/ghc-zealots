import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Yoel on 2016-02-16.
 */
public class CollectionUtils {

    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> collection, Comparator c) {
        List<T> list = new ArrayList<T>(collection);
        java.util.Collections.sort(list, c);
        return list;
    }

    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> collection) {
        List<T> list = new ArrayList<T>(collection);
        java.util.Collections.sort(list);
        return list;
    }
}
