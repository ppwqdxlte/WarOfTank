package observer;

/**
 * @author:李罡毛
 * @date:2021/2/21 11:50
 */
public class Dad implements Observer {
    @Override
    public void actionOnWakeUp(Event event) {
        System.out.println("啥事儿啊？？？"+event.getClass().getName());
        System.out.println("宝宝呦~~~我亲爱的宝~抱~~~~~~~~~~~~~~~~~");
    }
}
