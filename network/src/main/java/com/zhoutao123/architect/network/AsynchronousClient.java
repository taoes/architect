package com.zhoutao123.architect.network;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步IO操作
 */
public class AsynchronousClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        Future<Void> connect = channel.connect(new InetSocketAddress(9090));

        // 等待连接成功
        while (!connect.isDone()) {
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("连接Socket成功....");

        // 写数据
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextLine = scanner.nextLine();
            if ("exit".equalsIgnoreCase(nextLine)) {
                break;
            }

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(nextLine.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
            System.out.println("客户端:" + nextLine);


            // 等待读取数据
            ByteBuffer rBuffer = ByteBuffer.allocate(1024);
            channel.read(rBuffer);
            rBuffer.flip();

            // 读取有效的数据
            byte[] bytes = new byte[rBuffer.remaining()];
            rBuffer.get(bytes, 0, rBuffer.remaining());
            System.out.println("服务端:" + new String(bytes, StandardCharsets.UTF_8));
        }
        channel.close();
        System.out.println("系统:连接已关闭");
    }


}
