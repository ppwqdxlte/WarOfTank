package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.model.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/3/30 15:52
 */
public class TankJoinMsg extends Msg{
    public int x, y;    //8
    public Dir dir;     //4
    public boolean isMoving;//1
    public Group group;//4
    public UUID uuid;   //16   一共33个byte字节，1 byte = 8 bit;

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isMoving = moving;
        this.group = group;
        this.uuid = id;
    }

    public TankJoinMsg() {
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getName())
                .append("[")
                .append("uuid=" + uuid + " | ")
                //.append("name=" + name + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("isMoving=" + isMoving + " | ")
                .append("dir=" + dir + " | ")
                .append("group=" + group + " | ")
                .append("]");
        return stringBuilder.toString();
    }

    /**
     * @return 此tankJoinMsg对象的字节数组
     * Msg自己操作自己转化成字节数组
     */
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(isMoving);
            dos.writeInt(group.ordinal());
            dos.writeLong(uuid.getMostSignificantBits());
            dos.writeLong(uuid.getLeastSignificantBits());
//            dos.writeUTF(name);
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public void handle(Client client,Msg myTankJoinMsg) {
        //只处理别人的新坦克，已存在的坦克不处理
        if (client.getGameModel().getGameObjectWithUUID(this.uuid) == null) {
            System.out.println(this);
            Tank newTank = new Tank(this, client.getGameModel());
            client.getGameModel().getGameObjects().add(newTank);
            client.getGameModel().getGoMap().put(this.uuid, newTank);
            //然后告诉别人，自己的坦克
            client.getChannel().writeAndFlush(myTankJoinMsg);
        }
    }

    /**
     * @param bytes 包含TankJoinMsg信息的字节数组
     *              让tankJoinMsg对象自己解析并修改属性值
     */
    public void parse(byte[] bytes){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.isMoving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.uuid = new UUID(dis.readLong(), dis.readLong());
//           this.name = dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
