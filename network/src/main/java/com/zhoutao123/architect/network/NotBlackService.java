package com.zhoutao123.architect.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 非阻塞IO线程模型
 *
 * <li>创建 epoll_create() </li>
 *
 * @apiNote 当各个线程都比较活跃的情况下，SELECT模型会比epoll模型更加的高效，也就是说 C(活跃)/C(总数) 越大使用模型越高效
 */
public class NotBlackService {

    private static final List<SocketChannel> channelList = new ArrayList<>();

    // 处理效果等同于Linux系统中的SELECT模型，每次需要遍历所有的连接 以及遍历每个连接的特性
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9091));
        channel.configureBlocking(false);
        System.out.println("服务启动成功.....");
        while (true) {
            SocketChannel accept = channel.accept();
            if (accept != null) {
                System.out.println("连接成功");
                accept.configureBlocking(false);
                channelList.add(accept);
            }

            // FIXME: 需要处理所有的连接，连接越多性能越差，每次循环有大量的空转操作
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel socketChannel = iterator.next();
                ByteBuffer buffer = ByteBuffer.allocate(128);
                int read = socketChannel.read(buffer);
                if (read > 0) {
                    System.out.println("接收到数据:" + new String(buffer.array()));
                } else if (read == -1) {
                    System.out.println("连接断开");
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 支持Selector事件的连接模型
     */
    private static void nio() throws IOException {

        // 创建服务端channel，这里的底层实现是 epoll_create(int) 函数
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9090));
        channel.configureBlocking(false);

        // Selector 多路复用器 ep0ll
        Selector selector = Selector.open();

        // 注册对连接事件的监听，当有新的连接连接成功的时候，会将该channel放到活跃的集合中
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 阻塞需要等待的事件发生,目前已注册了 OP_ACCEPT 事件以及 OP_READ
            // 这里的底层实现是 epoll_wait() 函数
            selector.select();

            // 获取所有活跃的集合，准备遍历
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();


            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                // 如果该事件是连接成功的消息的事件
                if (key.isAcceptable()) {
                    ServerSocketChannel serviceChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serviceChannel.accept();
                    clientChannel.configureBlocking(false);
                    serviceChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功!");
                    continue;
                }

                // 如果该事件是有新的数据传输过来
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int len = clientChannel.read(buffer);
                    if (len > 0) {
                        System.out.println("接收到数据:" + new String(buffer.array()));
                    } else if (len == -1) {
                        System.out.println("连接断开");
                        clientChannel.close();
                    }
                }


                // 处理完成后需要移除，防止下次重复处理
                iterator.remove();
            }
        }
    }
}
