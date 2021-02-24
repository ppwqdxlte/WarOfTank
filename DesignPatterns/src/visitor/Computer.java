package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 16:52
 */
public class Computer {

    ComputerPart cpu;
    ComputerPart memory;
    ComputerPart hardDisc;

    public Computer(ComputerPart cpu, ComputerPart memory, ComputerPart hardDisc) {
        this.cpu = cpu;
        this.memory = memory;
        this.hardDisc = hardDisc;
    }

    public void accept(Visitor visitor){
        cpu.accept(visitor);
        memory.accept(visitor);
        hardDisc.accept(visitor);
    }
}
