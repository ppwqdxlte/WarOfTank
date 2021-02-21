package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/21 12:58
 */
public class Test {
    public static void main(String[] args) {
        Button button = new Button();
        button.addActionListener(new KeyPressActionListener1());
        button.addActionListener(new KeyPressActionListener2());
        button.pressedDown();
    }
}
class Button{
    private List<ActionListener> actionListeners = new ArrayList<>();
    public void pressedDown(){
        ActionEvent actionEvent = new ActionEvent(System.currentTimeMillis(),this);
        for (int i = 0; i < actionListeners.size(); i++) {
            actionListeners.get(i).actionPerformed(actionEvent);
        }
    }
    public void addActionListener(ActionListener actionListener){
        this.actionListeners.add(actionListener);
    }
}
class ActionEvent {
    long when;
    Object source;
    public ActionEvent(long when,Object source){
        super();
        this.when = when;
        this.source = source;
    }

    public long getWhen() {
        return when;
    }

    public Object getSource() {
        return source;
    }
}
interface ActionListener{
    void actionPerformed(ActionEvent actionEvent);
}
class KeyPressActionListener1 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("key press down 1");
    }
}
class KeyPressActionListener2 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("key press down 2");
    }
}
