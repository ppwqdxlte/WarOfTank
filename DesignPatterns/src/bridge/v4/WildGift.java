package bridge.v4;

public class WildGift implements Gift{
    private GiftImpl gift;

    public WildGift(GiftImpl gift) {
        this.gift = gift;
    }

    @Override
    public void show() {
        System.out.println("狂野的…………");
        System.out.println(gift.getName());
    }
}
