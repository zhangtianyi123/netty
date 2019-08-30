package zty.practise.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 提供channelHandler并引导netty服务端
 * @author zhangtianyi
 *
 */
public class ServerWithHandler {

	public static void startServer(final int port, final ChannelHandler... handlers) throws InterruptedException {
		//接受连接  serverHandler
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//处理客户端连接的所有（事件和IO）   childHandler
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//服务器端引导
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			//指定为ServerChannel和Channel处理所有事件的group(必要）
			serverBootstrap.group(bossGroup, workerGroup)
			//serverChannel使用NIO Selector接收连接（必要）
						   .channel(NioServerSocketChannel.class)
            //指定处理channel请求事件的处理器（必要）			   
						   .childHandler(new ChannelInitializer<SocketChannel>() {
							   @Override
							   public void initChannel(SocketChannel ch) {
								   ch.pipeline().addLast(handlers);
							   }
						   })
			//配置serverSocket的处理线程全满时，临时存放三次握手请求的队列最大长度（synQueue+acceptQueue）
						   .option(ChannelOption.SO_BACKLOG, 128)
		    //接收channel之后配置心跳保活机制
						   .childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture f = serverBootstrap.bind(port).sync(); // (7)
            f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
		}
		
	}
}
