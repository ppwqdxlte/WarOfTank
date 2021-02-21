package observer;

/**
 * @author:李罡毛
 * @date:2021/2/21 11:42
 */
public class GrandMa implements Observer {
    @Override
    public void actionOnWakeUp(Event event) {
        System.out.println("啥事兒啊？？？"+event.getClass().getName());
        System.out.println("宝宝不哭，抱抱宝宝~~~~~~~~");
    }
}
