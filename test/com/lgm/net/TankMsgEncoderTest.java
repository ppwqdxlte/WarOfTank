package com.lgm.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TankMsgEncoderTest {

    @Test
    void encode() {
        TankMsg tankMsg = new TankMsg(10,100);
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TankMsgEncoder());
        embeddedChannel.writeOutbound(tankMsg);
        ByteBuf byteBuf = (ByteBuf)embeddedChannel.readOutbound();
        Assert.assertEquals(tankMsg.x,byteBuf.readInt());
        Assert.assertEquals(tankMsg.y,byteBuf.readInt());
    }

}