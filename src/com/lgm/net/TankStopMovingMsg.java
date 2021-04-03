package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.model.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/4/3 15:41
 */
public class TankStopMovingMsg extends Msg{
    public int x,y;
    public Dir dir;
    public UUID id;
    @Override
    byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
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
    void parse(byte[] bytes) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(), dis.readLong());
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

    @Override
    void handle(Client client, Msg msg) {
        //只处理别人的坦克，不处理自己的坦克
        if (this.id == msg.getUuid()) return;
        System.out.println("停止！"+this.id);
        Tank tank = (Tank) client.getGameModel().getGameObjectWithUUID(this.id);
        tank.setX(this.getX());
        tank.setY(this.getY());
        tank.setDir(this.getDir());
        tank.setIsMoving(false);
    }

    @Override
    MsgType getMsgType() {
        return MsgType.TankStopMoving;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getName())
                .append("[")
                .append("uuid=" + id + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("dir=" + dir + " | ")
                .append("]");
        return stringBuilder.toString();
    }

    public TankStopMovingMsg() { }

    public TankStopMovingMsg(UUID id, int x, int y, Dir dir) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public TankStopMovingMsg(Tank tank) {
        this.id = tank.getUuid();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Dir getDir() {
        return dir;
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public UUID getUuid() {
        return id;
    }
    public void setUuid(UUID uuid) {
        this.id = uuid;
    }
}
