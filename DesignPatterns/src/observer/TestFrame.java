package observer;

import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/2/21 13:37
 */
public class TestFrame extends Frame {

    public void launch(){
        Button button = new Button();
        button.setLabel("press me");
        button.addActionListener(new KeyActionListener1());
        button.addActionListener(new KeyActionListener2());
        this.add(button);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setLocation(500,500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame().launch();
    }
}
class KeyActionListener1 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource().getClass());
        System.out.println("Key pressed down 01");
    }
}
class KeyActionListener2 implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        ((Button)e.getSource()).setLabel("Press me again!");
        System.out.println("Key pressed down 02");
    }
}
