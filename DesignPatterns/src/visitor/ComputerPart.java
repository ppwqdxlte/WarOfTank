package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 21:00
 */
public abstract class ComputerPart {
    abstract double showPrice();
    abstract void accept(Visitor visitor);
}
