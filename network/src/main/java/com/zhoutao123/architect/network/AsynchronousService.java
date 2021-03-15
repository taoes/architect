package com.zhoutao123.architect.network;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 异步IO操作
 */
public class AsynchronousService {

    public static void main(String[] args) throws IOException {
        new Thread(new AIOService()).start();
    }


    static class AIOService implements Runnable {

        public AsynchronousServerSocketChannel channel;

        private void init() throws IOException {
            channel = AsynchronousServerSocketChannel.open();
            channel.bind(new InetSocketAddress(9090));
            channel.accept(this, new AsynchronousHandler());
        }

        @Override
        public void run() {
            try {
                init();
                System.out.println("AIO 服务启动成功!");
                TimeUnit.HOURS.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
                System.out.println("AIO 服务启动出现异常");
                e.printStackTrace();
            }
        }
    }

    static class AsynchronousHandler implements CompletionHandler<AsynchronousSocketChannel, AIOService> {

        @Override
        public void completed(AsynchronousSocketChannel socketChannel, AIOService attachment) {
            // 重点: 重新监听
            attachment.channel.accept(attachment, this);

            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 异步的读取数据
            socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    attachment.flip();
                    byte[] bytes = new byte[attachment.remaining()];
                    attachment.get(bytes, 0, attachment.remaining());
                    System.out.println("客户端:" + new String(bytes, StandardCharsets.UTF_8));

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = format.format(new Date());
                    ByteBuffer wBuffer = ByteBuffer.wrap(currentTime.getBytes());
                    wBuffer.put(currentTime.getBytes());
                    wBuffer.flip();
                    socketChannel.write(wBuffer);
                    System.out.println("发送数据完成");
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("读取数据出现异常");
                    exc.printStackTrace();
                }
            });

        }

        @Override
        public void failed(Throwable exc, AIOService attachment) {
            System.out.println("连接Channel出现异常");
            exc.printStackTrace();

        }
    }
}
