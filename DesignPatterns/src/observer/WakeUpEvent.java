package observer;

/**
 * @author:李罡毛
 * @date:2021/2/21 11:25
 */
public class WakeUpEvent implements Event<Child>{
    private Child source;

    public WakeUpEvent(Child source) {
        this.source = source;
    }

    @Override
    public Child getSource() {
        return source;
    }
}
