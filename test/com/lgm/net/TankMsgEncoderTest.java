package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TankMsgEncoderTest {

    @Test
    void encode() {
        TankJoinMsg tankMsg = new TankJoinMsg(300,200, Dir.UP,false, Group.BAD, UUID.randomUUID());
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TankJoinMsgEncoder());
        embeddedChannel.writeOutbound(tankMsg);
        ByteBuf byteBuf = (ByteBuf)embeddedChannel.readOutbound();
        Assert.assertEquals(tankMsg.x,byteBuf.readInt());
        Assert.assertEquals(tankMsg.y,byteBuf.readInt());
    }

}