import java.util.Set;

/**
 * Created by Yoel on 2016-02-15.
 */
public class CellUtils {

    public static Cell getCellWithMinDistance(Cell currentCell, Set<Cell> cells){
        int minDistance = Integer.MAX_VALUE;
        int currentDistance;
        Cell cellWithMinDistance = null;

        for (Cell cell : cells) {
            currentDistance = currentCell.getDistance(cell);
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                cellWithMinDistance = cell;
            }
        }

        return cellWithMinDistance;
    }
}
