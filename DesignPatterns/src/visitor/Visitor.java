package visitor;

public interface Visitor {
    void visitCPU(CPU cpu);
    void visitHarddisc(Harddisc harddisc);
    void visitMemory(Memory memory);

    double getTotalPrice();
}
