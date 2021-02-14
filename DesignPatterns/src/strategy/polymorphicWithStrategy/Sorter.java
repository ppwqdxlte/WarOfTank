package strategy.polymorphicWithStrategy;

import DesignPatterns.strategy.polymorphicWithStrategy.Comparator;

/**
 * @author:李罡毛
 * @date:2021/2/12 13:16
 * 单粒冒泡排序类
 */
public class Sorter<T>{

    private Sorter(){}
    public static Sorter getInstance(){
        return SorterHolder.INSTANCE;
    }
    private static class SorterHolder{
        private static final Sorter INSTANCE = new Sorter();
    }

    public void sort(T[] arrs, Comparator<T> comparator){
        for (int i = 0; i < arrs.length; i++) {
            int minPos = i;
            for (int j = i; j < arrs.length; j++) {
                minPos = comparator.compare(arrs[i],arrs[j])>=0?i:j;
                swap(arrs,minPos,i);
            }
        }
    }

    private void swap(T[] arrs, int minPos, int i) {
        T temp = arrs[i];
        arrs[i] = arrs[minPos];
        arrs[minPos] = temp;
    }

}
