package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/21 11:24
 */
public class Child {
    private List<Observer> observers = new ArrayList<>();
    public Child(){
        observers.add(new GrandMa());
        observers.add(new Mum());
        observers.add(new Dad());
        observers.add((e)-> System.out.println("不哭不哭~~~~"));//钩子函数，本质也是观察者模式
    }
    public void wakeUp(){
        System.out.println("寶寶醒了，哇哇哭，嗚嗚嗚嗚嗚嗚");
        WakeUpEvent wakeUpEvent = new WakeUpEvent(this);
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).actionOnWakeUp(wakeUpEvent);
        }

    }
}
