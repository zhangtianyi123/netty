package zty.practise.netty.channelhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;


@Sharable
public class MyInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
	}
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		 super.userEventTriggered(ctx, evt);
	}
	 
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		 super.channelWritabilityChanged(ctx);
	}
	 
	/**
	 * 每一“块”数据传入的时候调用
	 * 收到数据后将数据写回客户端（写入缓冲区而不真正调用socket写）
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf in = (ByteBuf)msg;
		System.out.println("Server received:" + in.toString(CharsetUtil.UTF_8));
		//写操作是异步的
		ctx.write(in);
	}
	
	/**
	 * 当前一组客户端写入的数据读完时调用
	 * 收到数据后将数据写回客户端 真正冲刷数据，调用socket的写动作
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		//释放资源
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	/**
	 * 读操作时发生异常调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
