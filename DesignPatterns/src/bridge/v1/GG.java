package bridge.v1;

/**
 * @author:李罡毛
 * @date:2021/2/28 22:34
 */
public class GG {
    public void chase(MM mm){
        Gift gift = new Book();
        give(mm,gift);
    }
    public void chase(MM m, bridge.v4.Gift gift){
        System.out.println("哥哥送美眉一个");
        gift.show();
    }
    public void give(MM mm,Gift gift){
        System.out.println("gege give mm a gift");
    }
}
