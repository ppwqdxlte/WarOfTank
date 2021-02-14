package strategy.polymorphicWithoutStrategy;

import java.util.Arrays;

/**
 * @author:李罡毛
 * @date:2021/2/12 11:39
 */
public class Test {
    public static void main(String[] args) {
        Cat[] cats ={new Cat(10,10),new Cat(2,3),new Cat(5,5)};
        Dog[] dogs ={new Dog(1),new Dog(3),new Dog(5)};
        Sorter sorter = Sorter.getInstance();
        sorter.sort(cats);
        sorter.sort(dogs);
        Arrays.stream(cats).forEach(System.out::println);
        Arrays.stream(dogs).forEach(System.out::println);
    }
}
