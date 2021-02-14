package strategy.polymorphicWithoutStrategy;

import DesignPatterns.strategy.polymorphicWithoutStrategy.Comparable;

/**
 * @author:李罡毛
 * @date:2021/2/12 11:12
 * 单粒工具类
 */
public class Sorter<T> {

    private Sorter(){}
    private static volatile Sorter sorter;
    public static Sorter getInstance(){
        if (sorter == null){
            synchronized (Sorter.class){
                if (sorter == null){
                    sorter = new Sorter();
                }
            }
        }
        return sorter;
    }

    public void sort(Comparable<T>[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minPos = i;
            for (int j = i; j < arr.length; j++) {
                minPos = arr[i].compareTo((T)arr[j])>=0?i:j;
                swap(arr,minPos,i);
            }
        }
    }

    /**
     * @param arr 数组
     * @param minPos 最小值的下标
     */
    private void swap(Comparable<T>[] arr,int minPos,int i){
        Comparable<T> tComparable = arr[i];
        arr[i] = arr[minPos];
        arr[minPos] = tComparable;
    }
}
