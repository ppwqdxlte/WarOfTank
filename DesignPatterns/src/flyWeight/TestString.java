package flyWeight;

/**
 * @author:李罡毛
 * @date:2021/2/21 15:56
 */
public class TestString {
    public static void main(String[] args) {
        String abc1 = "abc";
        String abc2 = "abc";
        String abc3 = new String("abc");
        String abc4 = new String("abc");
        System.out.println(abc1==abc2);
        System.out.println(abc2==abc3);
        System.out.println(abc3==abc4);
        System.out.println(abc3.intern() == abc1);
    }
}
