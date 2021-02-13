package DesignPatterns.strategy.polymorphicWithStrategy;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/2/12 13:32
 */
public class Test {
    public static void main(String[] args) {
        Cat[] cats = {new Cat(10,10),new Cat(3,6),new Cat(4,4),new Cat(5,5)};
        Dog[] dogs = {new Dog(3),new Dog(6),new Dog(1),new Dog(5)};
        Sorter sorter = Sorter.getInstance();
        sorter.sort(cats,new CatWeightComparator());
        sorter.sort(dogs,new DogAppetiteComparator());
        Arrays.stream(cats).forEach(System.out::println);
        Arrays.stream(dogs).forEach(System.out::println);
        sorter.sort(cats,new CatHeightComparator());
        Arrays.stream(cats).forEach(System.out::println);
    }
}
