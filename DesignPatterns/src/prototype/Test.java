package prototype;

/**
 * @author:李罡毛
 * @date:2021/3/8 21:37
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Bullet bullet = new Bullet(10,3,new Location(100,100));
        Bullet bullet1 = bullet.clone();
        System.out.println(bullet == bullet1);
        System.out.println(bullet.location == bullet1.location);
    }
}
