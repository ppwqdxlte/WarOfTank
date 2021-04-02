package com.lgm.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:李罡毛
 * @date:2021/3/29 22:38
 */
public class ServerFrame extends Frame {

    private TextArea taLeft = new TextArea();
    private TextArea taRight = new TextArea();
    private Server server;

    private static class ServerFrameInstance{
        static final ServerFrame INSTANCE = new ServerFrame();
    }

    public static ServerFrame getINSTANCE(){
        return ServerFrameInstance.INSTANCE;
    }

    private void start(){
        ServerFrame.getINSTANCE().server = new Server();
        ServerFrame.getINSTANCE().server.serverStart();
    }

    private ServerFrame(){
        this.setSize(800,600);
        this.setLocation(350,300);
        Panel panel = new Panel(new GridLayout(1,2));
        panel.add(taLeft);
        panel.add(taRight);
        this.add(panel);
        taLeft.setFont(new Font("verderna",Font.PLAIN,25));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (server!=null) server.shutDown();
            }
        });
        this.setVisible(true);
    }
    public void updateServerMsg(String msg){
        this.taLeft.setText(taLeft.getText() + msg + System.getProperty("line.separator"));
    }

    public void updateClientMsg(String string) {
        this.taRight.setText(taRight.getText() + string + System.getProperty("line.separator"));
    }

    public static void main(String[] args) {
        ServerFrame.getINSTANCE().start();
    }
}
