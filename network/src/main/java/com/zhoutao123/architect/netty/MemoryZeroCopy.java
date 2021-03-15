package com.zhoutao123.architect.netty;

import java.nio.ByteBuffer;

/**
 * netty 内存零拷贝
 */
public class MemoryZeroCopy {


    public static void main(String[] args) {

        // 申请的是heap堆存空间 HeapByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(128);
        for (int i = 0; i < 128 / 2; i++) {
            buffer.putChar((char) i);
        }
        System.out.println();

        // 申请的是直接内存空间 DirectByteBuffer
        buffer = ByteBuffer.allocateDirect(1000);
    }


}
