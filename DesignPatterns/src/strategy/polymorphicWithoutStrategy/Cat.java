package strategy.polymorphicWithoutStrategy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author:李罡毛
 * @date:2021/2/12 11:04
 */
public class Cat implements Serializable, Comparable<Cat> {
    @Serial
    private static final long serialVersionUID = -4425668413067543691L;
    private int weight;
    private int height;

    public Cat() {
    }

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return weight == cat.weight && height == cat.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, height);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public int compareTo(Cat o) {
        if (this.getWeight()>o.getWeight())return -1;
        else if (this.getWeight()<o.getWeight())return 1;
        return 0;
    }
}
