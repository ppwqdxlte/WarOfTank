package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 16:56
 */
public class CPU extends ComputerPart{
    @Override
    double showPrice() {
        return 2300;
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitCPU(this);
    }
}
