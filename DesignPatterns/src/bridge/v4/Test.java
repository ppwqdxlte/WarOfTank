package bridge.v4;

import bridge.v1.GG;
import bridge.v1.MM;

/**
 * @author:李罡毛
 * @date:2021/2/28 22:54
 */
public class Test {
    public static void main(String[] args) {
        Gift g1 = new WarmGift(new Book());
        Gift g2 = new WarmGift(new Flower());
        Gift g3 = new WildGift(new Book());
        Gift g4 = new WildGift(new Flower());

        GG gg = new GG();
        MM mm = new MM();
        gg.chase(mm,g1);
        gg.chase(mm,g2);
        gg.chase(mm,g3);
        gg.chase(mm,g4);
    }
}
