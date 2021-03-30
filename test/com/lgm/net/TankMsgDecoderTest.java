package com.lgm.net;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TankMsgDecoderTest {

    /**
     * c->s方向
     * 站在s的角度测试连通性
     */
    @Test
    void decode() {
        TankMsg tankMsg = new TankMsg(200,100);
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TankMsgEncoder(),new TankMsgDecoder());
        embeddedChannel.writeInbound(tankMsg);
        TankMsg readMsg = (TankMsg) embeddedChannel.readInbound();
        Assert.assertEquals(readMsg.x,tankMsg.x);
        Assert.assertEquals(readMsg.y,tankMsg.y);
    }
}