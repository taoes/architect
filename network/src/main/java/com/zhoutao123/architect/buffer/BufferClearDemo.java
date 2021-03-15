package com.zhoutao123.architect.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BufferClearDemo {

    public static void main(String[] args) {


        // 在堆内存中申请的ByteBuffer,其实际类型为 HeapByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 在直接内存中申请的ByteBuffer,其实际类型为 DirectByteBuffer
        buffer = ByteBuffer.allocateDirect(10);


        // 通过包装的方式创建ByteBuffer，显然通过这种方式创建的ByteBuffer肯定是HeapByteBuffer
        byte[] bytes = "Hello,Java ByteBuffer".getBytes(StandardCharsets.UTF_8);
        buffer = ByteBuffer.wrap(bytes);
        buffer = ByteBuffer.wrap(bytes, 0, 100);


        // 复制缓存区，复制后两个缓存区具有不同的索引值，但是其共享内存数据
        ByteBuffer newBuffer = buffer.duplicate();

        buffer.put((byte) 'H')
                .put((byte) 'e')
                .put((byte) 'l')
                .put((byte) 'l')
                .put((byte) 'o');

        // 模拟清除数据
        buffer.clear();

        // 尝试读取前4个字符 并断言
        assert buffer.get() == (byte) 'H';
        assert buffer.get() == (byte) 'e';
        assert buffer.get() == (byte) 'l';
        assert buffer.get() == (byte) 'l';
        assert buffer.get() == (byte) 'o';
    }
}
