package strategy.polymorphicWithStrategy;

/**
 * @author:李罡毛
 * @date:2021/2/12 13:21
 */
public class CatWeightComparator implements Comparator<Cat>{
    @Override
    public int compare(Cat t1, Cat t2) {
        if (t1.getWeight()>t2.getWeight())return -1;
        else if (t1.getWeight()<t2.getWeight())return 1;
        return 0;
    }
}
