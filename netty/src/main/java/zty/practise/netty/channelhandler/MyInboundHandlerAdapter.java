package zty.practise.netty.channelhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;


@Sharable
public class MyInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
	
	@Override
    public void channelRegistered(ChannelHandlerContext ctx) {
		
	}
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) {
		
	}
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
		 
	}
	 
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) {
		 
	}
	 
	/**
	 * 每一“块”数据传入的时候调用
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
	}
	
	/**
	 * 当前一组客户端写入的数据读完时调用
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		
	}
	
	/**
	 * 读操作时发生异常调用
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		
	}
}
