package com.lgm.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author:李罡毛
 * @date:2021/3/29 21:58
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static Channel channel;
    public void serverStart(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new TankJoinMsgEncoder())//codec的添加顺序不影响结果
                                    .addLast(new TankJoinMsgDecoder())
                                    .addLast(new ServerChildHandler());
                        }
                    })
                    .bind(8888)
                    .sync();
            ServerFrame.getINSTANCE().updateServerMsg("Server is started!");
            channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void shutDown(){
        System.out.println("正在关闭服务器。。。");
        channel.close();
        System.exit(0);
    }
}

class ServerChildHandler extends SimpleChannelInboundHandler<TankJoinMsg>{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
        ServerFrame.getINSTANCE().updateClientMsg(ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
        System.out.println(msg);
        Server.clients.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getLocalizedMessage()+ctx.channel().remoteAddress()+"shut down!");
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
