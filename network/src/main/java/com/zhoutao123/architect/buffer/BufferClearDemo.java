package com.zhoutao123.architect.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


/**
 * <li> 在堆内存中申请的ByteBuffer,其实际类型为  {@link java.nio.HeapByteBuffer}</>
 * <li>而在直接内存中申请的ByteBuffer，其实际类型为 {@link java.nio.DirectByteBuffer}</li>
 */
public class BufferClearDemo {


    public static void main(String[] args) {
        ByteBuffer heapBuffer = ByteBuffer.allocate(10);
        System.out.println("堆内存的的Buffer的类类型：" + heapBuffer.getClass().getName());


        // 在直接内存中申请的ByteBuffer,其实际类型为 DirectByteBuffer
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);
        System.out.println("直接内存的的Buffer的类类型：" + directBuffer.getClass().getName());

        // 通过包装的方式创建ByteBuffer，显然通过这种方式创建的ByteBuffer肯定是HeapByteBuffer
        byte[] bytes = "Hello,Java ByteBuffer".getBytes(StandardCharsets.UTF_8);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);


        // 复制缓存区，复制后两个缓存区具有不同的索引值，但是其共享内存数据
        ByteBuffer newBuffer = buffer.duplicate();

        // 复制后的索引为 java.nio.HeapByteBuffer[pos=0 lim=21 cap=21]
        System.out.println(newBuffer);

        // 写入数据
        buffer.put((byte) 'H')
                .put((byte) 'e')
                .put((byte) 'l')
                .put((byte) 'l')
                .put((byte) 'o');

        // 模拟清除数据
        buffer.clear();
        // 释放后的数据索引为 java.nio.HeapByteBuffer[pos=0 lim=21 cap=21]
        System.out.println(buffer);

        // 尝试读取前4个字符 并断言
        assert buffer.get() == (byte) 'H';
        assert buffer.get() == (byte) 'e';
        assert buffer.get() == (byte) 'l';
        assert buffer.get() == (byte) 'l';
        assert buffer.get() == (byte) 'o';
    }
}
