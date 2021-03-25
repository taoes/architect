package com.zhoutao123.architect.network;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlackService {

    public static void main(String[] args) throws IOException {
        Class<? extends String[]> aClass = args.getClass();
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(10011));
        while (true) {
            // 阻塞操作，等待连接
            Socket accept = socket.accept();
            System.out.println("接收到连接后创建线程处理数据");
            new Thread(
                    () -> {
                        // 轮询等待接收
                        try (InputStream stream = accept.getInputStream(); OutputStream os = accept.getOutputStream()) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                            while (true) {
                                String s = reader.readLine();
                                if (s.contains("byte")) {
                                    System.out.println("接收到程序退出命令:" + s);
                                    return;
                                }
                                String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                                os.write((format + ":已成功执行" + s).getBytes());
                                os.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    })
                    .start();
        }
    }

    /**
     * 优化BIO线程模型，使用线程池的方式
     *
     * @throws IOException 当数据连接失败的时候抛出此异常
     */
    private void handleBIOWithThreadPool() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(9091));
        while (true) {
            Socket clientSocket = socket.accept();
            executor.submit(new SocketTask(clientSocket));
        }
    }

    static class SocketTask implements Runnable {

        private Socket socket;

        public SocketTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream stream = this.socket.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String result = reader.readLine();
                System.out.println("接收到数据:" + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
