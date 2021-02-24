package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 21:21
 */
public class StudentVisitor implements Visitor{

    double totalPrice = 0.0;

    @Override
    public void visitCPU(CPU cpu) {
        totalPrice += cpu.showPrice()*0.9;
    }

    @Override
    public void visitHarddisc(Harddisc harddisc) {
        totalPrice += harddisc.showPrice()*0.9;
    }

    @Override
    public void visitMemory(Memory memory) {
        totalPrice += memory.showPrice()*0.9;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }
}
