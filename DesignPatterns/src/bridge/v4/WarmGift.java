package bridge.v4;

public class WarmGift implements Gift{
    private GiftImpl gift;

    public WarmGift(GiftImpl gift) {
        this.gift = gift;
    }

    @Override
    public void show() {
        System.out.println("温暖的...");
        System.out.println(gift.getName());
    }
}
