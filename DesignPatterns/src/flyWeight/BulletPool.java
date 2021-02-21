package flyWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/21 15:58
 */
public class BulletPool {
    private List<Bullet> bulletList = new ArrayList<>();

    public BulletPool(int size){
        for (int i = 0; i < size; i++) {
            add(new Bullet());
        }
    }

    private void add(Bullet bullet){
        bulletList.add(bullet);
    }
    public Bullet getOne(){
        for (int i = 0; i < bulletList.size(); i++) {
            if (!bulletList.get(i).isLive) {
                System.out.println("复活一个子弹");
                bulletList.get(i).isLive = true;
                return bulletList.get(i);
            }
        }
        System.out.println("创建一个子弹");
        Bullet bullet = new Bullet();
        bulletList.add(bullet);
        return bullet;
    }
}
