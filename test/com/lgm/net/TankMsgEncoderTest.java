package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class TankMsgEncoderTest {

    /**
     * c->s 编码器
     */
    @Test
    void encode() {
        TankJoinMsg tankMsg = new TankJoinMsg(300,200, Dir.UP,false, Group.BAD, UUID.randomUUID());
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new TankMsgEncoder());
        embeddedChannel.writeOutbound(tankMsg);
        ByteBuf byteBuf = (ByteBuf)embeddedChannel.readOutbound();
        byteBuf.markReaderIndex();
        byteBuf.readInt();
        int len = byteBuf.readInt();
        if (byteBuf.readableBytes()<len){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[len];
        byteBuf.readBytes(bytes);
        TankJoinMsg tankJoinMsg = new TankJoinMsg();
        tankJoinMsg.parse(bytes);
        Assert.assertTrue(tankJoinMsg.x==tankMsg.x);
    }

    /**
     * c添加encoder、decoder，
     * 分别测试Inbound Outbound两个方向
     * 的类型转换以及连通性
     * 【结论】：
     *          c往外写，只走了Encoder，s接收到ByteBuf类型的数据；
     *          s往c写，c往里读，只走了Decoder，c接收到的是TankJoinMsg类型的数据；
     *          也就是说c端即便是添加了两个netty.codec，每个codec都在不同方向上生效；
     *          而且，codec过滤器的添加顺序不影响结果！
     *          上述机制是netty底层自动实现的。
     */
    @Test
    void client_Add_Encoder$Decoder(){
        TankJoinMsg tankMsg = new TankJoinMsg(300,200, Dir.UP,false, Group.BAD, UUID.randomUUID());
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                new TankMsgDecoder(),new TankMsgEncoder());
        embeddedChannel.writeOutbound(tankMsg);
        Object outboundMsg = null;
        Assert.assertTrue((outboundMsg = embeddedChannel.readOutbound()) instanceof ByteBuf);
        embeddedChannel.writeInbound(outboundMsg);
        Assert.assertTrue(embeddedChannel.readInbound() instanceof TankJoinMsg);
    }

}