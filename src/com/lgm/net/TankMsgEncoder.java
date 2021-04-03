package com.lgm.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author:李罡毛
 * @date:2021/3/30 22:09
 */
public class TankMsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Msg tankMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(tankMsg.getMsgType().ordinal());
        byte[] bytes = tankMsg.toBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
