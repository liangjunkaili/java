package nio;

import java.nio.ByteBuffer;

public class BufferUtil {
    public static void useByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(16);
        System.out.println(buffer.capacity()+"=="+buffer.limit()+"=="+buffer.position());
        buffer.putInt(1);
//        buffer.mark();
        buffer.putInt(2);
//        buffer.reset();
        buffer.putInt(3);
//        buffer.limit(12);
        buffer.putInt(4);
        System.out.println(buffer.capacity()+"=="+buffer.limit()+"=="+buffer.position());
        buffer.flip();
        System.out.println(buffer.capacity()+"=="+buffer.limit()+"=="+buffer.position());
        while (buffer.hasRemaining()){
            int i = buffer.getInt();
            System.out.println(i);
        }
        System.out.println(buffer.capacity()+"=="+buffer.limit()+"=="+buffer.position());
        buffer.flip();
//        buffer.clear();
//        buffer.compact();
        System.out.println(buffer.capacity()+"=="+buffer.limit()+"=="+buffer.position());
        buffer.putInt(6);
        buffer.putInt(7);
//        buffer.putInt(8);
//        buffer.putInt(9);
        buffer.flip();
        while (buffer.hasRemaining()){
            int i = buffer.getInt();
            System.out.println(i);
        }
        buffer.rewind();
        while (buffer.hasRemaining()){
            int i = buffer.getInt();
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
//        useByteBuffer();
//        long endTime = System.currentTimeMillis();
//        long startTime = endTime-(30*24*60*60*1000L);
//        System.out.println(startTime-endTime);
//        System.out.println(startTime);
//        System.out.println(endTime);
        System.out.println(31308000/60000L);
    }
}
