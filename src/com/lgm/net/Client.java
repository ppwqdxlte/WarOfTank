package com.lgm.net;

import com.lgm.enumeration.Dir;
import com.lgm.enumeration.Group;
import com.lgm.facade.GameModel;
import com.lgm.model.Tank;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * @author:李罡毛
 * @date:2021/3/29 16:06
 */
public class Client {
    private Channel channel;
    private GameModel gameModel;
    private Client(){}
    public Client(GameModel gameModel){
        this.gameModel = gameModel;
    }
    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture channelFuture = bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer(this))
                    .connect("localhost", 8888);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        // initialize the channel
                        channel = future.channel();
                    }
                }
            }).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(String msg){
        ByteBuf byteBuf = Unpooled.copiedBuffer(msg.getBytes());
        this.channel.writeAndFlush(byteBuf);
    }

    public void closeConnect(){
        this.channel.close();
    }

    public Channel getChannel(){
        return this.channel;
    }

    public GameModel getGameModel(){
        return this.gameModel;
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{
    private Client client;
    public ClientChannelInitializer(Client client){
        super();
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new TankJoinMsgEncoder())
                .addLast(new TankJoinMsgDecoder())
                .addLast(new ClientChannelHandler(client));
    }
}

class ClientChannelHandler extends SimpleChannelInboundHandler<TankJoinMsg>{
    private Client client;
    public ClientChannelHandler(Client client){
        super();
        this.client = client;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       //完成主战坦克的初始化功能，删除下面测试代码【不得不删除，写了下面这个就无法收到mainTankJoinMsg】
        // ctx.writeAndFlush(new TankJoinMsg(300,200, Dir.UP,false, Group.BAD, UUID.randomUUID()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TankJoinMsg msg) throws Exception {
        //只处理别人的新坦克，已存在的坦克不处理，然后告诉别人自己的坦克
        if (this.client.getGameModel().getGameObjectWithUUID(msg.uuid) != null) return;
        System.out.println(msg);
        Tank newTank = new Tank(msg,this.client.getGameModel());
        this.client.getGameModel().getGameObjects().add(newTank);
        this.client.getGameModel().getGoMap().put(msg.uuid,newTank);
        Tank mainTank = (Tank) this.client.getGameModel().getGameObjects().get(0);
        TankJoinMsg myTankJoinMsg = new TankJoinMsg(mainTank.getX(),mainTank.getY(),mainTank.getDir()
                ,mainTank.getIsMoving(),mainTank.getGroup(),mainTank.getUuid());
        channelHandlerContext.writeAndFlush(myTankJoinMsg);
    }
}
