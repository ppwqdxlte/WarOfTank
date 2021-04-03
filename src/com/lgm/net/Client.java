package com.lgm.net;

import com.lgm.facade.GameModel;
import com.lgm.model.Tank;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
            channelFuture.addListener(future->{
                if (!future.isSuccess()) {
                System.out.println("not connected!");
            } else {
                System.out.println("connected!");
                // initialize the channel
                channel = ((ChannelFuture)future).channel();
            }}).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(Msg msg){
        this.channel.writeAndFlush(msg);
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
                .addLast(new TankMsgEncoder())
                .addLast(new TankMsgDecoder())
                .addLast(new ClientChannelHandler(client));
    }
}

class ClientChannelHandler extends SimpleChannelInboundHandler<Msg>{
    private Client client;
    private TankJoinMsg myTankJoinMsg;
    public ClientChannelHandler(Client client){
        super();
        this.client = client;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Tank mainTank = (Tank) this.client.getGameModel().getGameObjects().get(0);
        this.myTankJoinMsg = new TankJoinMsg(mainTank.getX(),mainTank.getY(),mainTank.getDir()
                ,mainTank.getIsMoving(),mainTank.getGroup(),mainTank.getUuid());
        //告诉别人，自己的坦克加进来了
        ctx.writeAndFlush(this.myTankJoinMsg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        msg.handle(this.client,this.myTankJoinMsg);
    }
}
