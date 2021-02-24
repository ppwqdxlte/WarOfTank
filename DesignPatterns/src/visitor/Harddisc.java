package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 16:55
 */
public class Harddisc extends ComputerPart{
    @Override
    double showPrice() {
        return 800;
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitHarddisc(this);
    }
}
