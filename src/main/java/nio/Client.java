package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    public static void testSocketChannel(){
        Selector selector = null;
        SocketChannel socketChannel = null;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            SocketAddress socketAddress = new InetSocketAddress("localhost",9999);
            socketChannel.connect(socketAddress);
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            while (selector.select()>0){
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()){
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    if (selectionKey.isValid()&&selectionKey.isConnectable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if (channel.isConnectionPending()) {
                            // channel.finishConnect()才能完成连接
                            channel.finishConnect();
                        }

                        channel.configureBlocking(false);

                        // 数据写入通道
                        channel.write(ByteBuffer.wrap(new String("Hello Server!").getBytes()));

                        // 通道注册到选择器，并且这个通道只对读事件感兴趣
                        channel.register(selector, SelectionKey.OP_READ);
                    }else if (selectionKey.isValid()&&selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        // 从通道读取数据到缓冲区
                        ByteBuffer buffer = ByteBuffer.allocate(128);
                        channel.read(buffer);

                        // 输出服务端响应发送过来的消息
                        byte[] data = buffer.array();
                        String msg = new String(data).trim();
                        System.out.println("client receive msg from server：" + msg);
                        channel.close();
                    }
                }
            }

//            while (!socketChannel.finishConnect()){
//                ByteBuffer buffer = ByteBuffer.allocate(1024);
//                buffer.putInt(1);
//                socketChannel.write(buffer);
//                buffer.clear();
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socketChannel.close();
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        testSocketChannel();
    }
}
