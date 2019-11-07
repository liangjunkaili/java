package nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class ChannelUtil {
    public static void testFileChannel(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\test.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\test1.txt");
            FileChannel inputStreamChannel = fileInputStream.getChannel();
            FileChannel outputStreamChannel = fileOutputStream.getChannel();
//            RandomAccessFile randomAccessFile = new RandomAccessFile("","rw");
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            int length = -1;
//            while ((length = inputStreamChannel.read(buffer))!=-1){
//                System.out.println(length);
//                buffer.flip();
//                int l = -1;
//                while ((l=outputStreamChannel.write(buffer))!=0){
//                    System.out.println(l);
//                }
//                buffer.clear();
//            }
//            long l = outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());
            long l = inputStreamChannel.transferTo(0,inputStreamChannel.size(),outputStreamChannel);
            System.out.println(l);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public static void testServerSockerChannel(){
        AtomicInteger integer = new AtomicInteger(0);
        Selector selector = null;
        ServerSocketChannel serverSocketChannel = null;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress("localhost",9999);
            serverSocketChannel.bind(socketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select()>0){
                System.out.println(integer.incrementAndGet());
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()){
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if (selectionKey.isValid()&&selectionKey.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.write(ByteBuffer.wrap(new String("Hello Client!").getBytes()));
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }else if (selectionKey.isValid()&&selectionKey.isReadable()){
                        System.out.println(selectionKey.isValid()+"=="+selectionKey.channel());
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        System.out.println(socketChannel.finishConnect()+"=="+socketChannel.isConnectionPending());
                        int read = socketChannel.read(buffer);
                        if (read > 0) {
                            byte[] data = buffer.array();
                            String msg = new String(data).trim();
                            System.out.println("服务端收到信息：" + msg);

                            // 回写数据， 将消息回送给客户端
                            ByteBuffer outBuffer = ByteBuffer.wrap("好的".getBytes());
                            socketChannel.write(outBuffer);
//                            socketChannel.close();
                        } else {
                            System.out.println("客户端关闭");
                            socketChannel.close();
                            selectionKey.cancel();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocketChannel.close();
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
//        testFileChannel();
//        testServerSockerChannel();
        for (;;){
            for (int i=0;i<9600000;i++)
                ;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
