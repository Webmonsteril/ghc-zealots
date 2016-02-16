import java.util.HashMap;

/**
 * Created by Yoel on 2016-02-15.
 */
public class Drone {
    private Cell location;

    private boolean available;

    private HashMap<Cell, HashMap<Integer, Integer>> task;

    public HashMap<Cell, HashMap<Integer, Integer>> getTask() {
        return task;
    }

    public void setTask(HashMap<Cell, HashMap<Integer, Integer>> task) {
        this.task = task;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Cell getLocation() {
        return location;
    }

    public void setLocation(Cell location) {
        this.location = location;
    }
}
