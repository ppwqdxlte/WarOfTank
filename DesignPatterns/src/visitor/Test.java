package visitor;

/**
 * @author:李罡毛
 * @date:2021/2/24 21:41
 */
public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer(new CPU(),new Memory(),new Harddisc());
        Visitor pv = new PersonalVisitor();
        Visitor sv = new StudentVisitor();
        Visitor cv = new CorpVisitor();
        computer.accept(pv);
        computer.accept(sv);
        computer.accept(cv);
        System.out.println(pv.getTotalPrice());
        System.out.println(sv.getTotalPrice());
        System.out.println(cv.getTotalPrice());
    }
}
