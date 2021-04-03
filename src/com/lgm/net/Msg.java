package com.lgm.net;

/**
 * @author:李罡毛
 * @date:2021/4/2 17:16
 */
public abstract class Msg {
    abstract byte[] toBytes();
    abstract void parse(byte[] bytes);
    abstract void handle(Client client,Msg msg);
    abstract MsgType getMsgType();
}
