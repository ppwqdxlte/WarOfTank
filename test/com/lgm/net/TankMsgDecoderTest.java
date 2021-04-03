package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TankMsgDecoderTest {

    /**
     * c->s方向
     * 站在s的角度测试连通性
     */
    @Test
    void decode() {
        TankJoinMsg tankMsg = new TankJoinMsg(300,200, Dir.UP,false, Group.BAD, UUID.randomUUID());
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TankMsgEncoder(),new TankMsgDecoder());
        embeddedChannel.writeInbound(tankMsg);
        TankJoinMsg readMsg = (TankJoinMsg) embeddedChannel.readInbound();
        Assert.assertEquals(readMsg.x,tankMsg.x);
        Assert.assertEquals(readMsg.y,tankMsg.y);
    }
}