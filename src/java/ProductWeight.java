import java.util.Comparator;

/**
 * Created by Yoel on 2016-02-16.
 */
public class ProductWeight implements Comparable<ProductWeight>{
    private Integer type;
    private Integer weight;

    public ProductWeight(){}

    public ProductWeight(Integer type, Integer weight) {
        this.type = type;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getType() {

        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int compareTo(ProductWeight otherProductWeight) {
        return weight - otherProductWeight.weight;
    }
}
