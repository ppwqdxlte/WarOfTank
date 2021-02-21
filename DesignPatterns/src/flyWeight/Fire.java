package flyWeight;

/**
 * @author:李罡毛
 * @date:2021/2/21 16:06
 */
public class Fire {
    public static void main(String[] args) {
        BulletPool bulletPool = new BulletPool(10);
        for (int i = 0; i < 15; i++) {
            bulletPool.getOne();
        }
    }
}
