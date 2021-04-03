package com.lgm.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author:李罡毛
 * @date:2021/3/30 22:09
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankJoinMsg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(tankJoinMsg.getMsgType().ordinal());
        byte[] bytes = tankJoinMsg.toBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
