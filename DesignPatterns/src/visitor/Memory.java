package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 16:55
 */
public class Memory extends ComputerPart{
    @Override
    double showPrice() {
        return 1000;
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitMemory(this);
    }
}
