package com.lgm.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/3/30 22:11
 */
public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) return;
        byteBuf.markReaderIndex();//标记
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();
        if (byteBuf.readableBytes()<length){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Msg msg = null;
        switch (msgType){
            case TankJoin:
                msg = new TankJoinMsg();
                msg.parse(bytes);
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                msg.parse(bytes);
                break;
            default:
                break;
        }
        list.add(msg);
    }
}
