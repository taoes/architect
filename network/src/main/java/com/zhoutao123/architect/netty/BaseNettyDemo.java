package com.zhoutao123.architect.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty 基础模型架构
 */
public class BaseNettyDemo {

    public static void main(String[] args) {

        /*
         * 创建事件循环组，其本质是在底层创建了 Selector,通过不断的追踪源码，可以发现，其本质是在创建了 {@link io.netty.util.concurrent.EventExecutor} 数组
         * 对 EventExecutor 不断的最终则会发现其会创建Selector实例 {@code this.selector = selectorTuple.selector;}
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1025)
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel c) throws Exception {
                                    c.pipeline().addLast(new NettyServerHandler());
                                }
                            });

            /*
             * bind 方法使用 channel 中的NioServerSocketChannel 创建了一个 NioServerSocketChannel 实例对象，而 NioServerSocketChannel
             * 实例对象的无参构造方法中则创建了一个 ServerSocketChannel {@link NioServerSocketChannel#NioServerSocketChannel()}
             * 这和NIO编程范式中的 {@link ServerSocketChannel#open()} 创建ServerSocketChannel 方法一致
             */
            ChannelFuture future = bootstrap.bind(9090).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
