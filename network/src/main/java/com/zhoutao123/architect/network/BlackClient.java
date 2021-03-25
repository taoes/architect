package com.zhoutao123.architect.network;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BlackClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 10011);
        System.out.println(socket.isConnected());
        OutputStream stream = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String content = scanner.nextLine();
            stream.write((content + "\r\n").getBytes());
            stream.flush();
            // 等待接收数据
            int len = in.read(bytes);
            if (len == -1) {
                System.out.println("未接收到数据....");
                return;
            }
            System.out.println(new java.lang.String(bytes, 0, len));
        }

    }


}
