package DesignPatterns.strategy.polymorphicWithStrategy;

/**
 * @author:李罡毛
 * @date:2021/2/12 13:25
 */
public class DogAppetiteComparator implements Comparator<Dog>{

    @Override
    public int compare(Dog t1, Dog t2) {
        if (t1.getAppetite()>t2.getAppetite())return -1;
        else if (t1.getAppetite()<t2.getAppetite())return 1;
        return 0;
    }
}
