package observer;

/**
 * @author:李罡毛
 * @date:2021/2/21 11:45
 */
public class Mum implements Observer {
    @Override
    public void actionOnWakeUp(Event event) {
        System.out.println("啥事儿啊？？？"+event.getClass().getName());
        System.out.println("宝宝不哭，宝宝洽嘚嘚~~~~~~~~~~");
    }
}
