package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.model.Bullet;
import com.lgm.model.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/4/3 20:10
 */
public class BulletNewMsg extends Msg{
    private UUID tankId;
    private UUID id;
    private int x,y;
    private Dir dir;
    public BulletNewMsg(){}
    public BulletNewMsg(Bullet bullet){
        super();
        this.tankId = bullet.getTankId();
        this.id = bullet.getUuid();
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
    }
    @Override
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
            dos.writeLong(tankId.getMostSignificantBits());
            dos.writeLong(tankId.getLeastSignificantBits());
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
    public void parse(byte[] bytes) {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(bytes));
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.tankId = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(),dis.readLong());
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
    public void handle(Client client, Msg msg) {
        //只处理别人的新子弹，自己的子弹不处理，UUID不能用==比较相等！要转化成值比较！
        if (msg.getUuid().toString().equals(this.getTankId().toString())) return;
        System.out.println("biu！"+this.tankId);
        Bullet newBullet = new Bullet(this.x,this.y,this.dir,this.tankId,client.getGameModel());
        client.getGameModel().getGameObjects().add(newBullet);
        client.getGameModel().getGoMap().put(this.id, newBullet);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }

    @Override
    public UUID getUuid() {
        return id;
    }

    public UUID getTankId(){
        return tankId;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getName())
                .append("[")
                .append("tankId=" + tankId + " | ")
                .append("uuid=" + id + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("dir=" + dir + " | ")
                .append("]");
        return stringBuilder.toString();
    }
}
