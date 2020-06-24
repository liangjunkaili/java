package nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioServer {
    public static void main(String[] args) {
        new AioServer().init();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        try {
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress(9898));
            assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    SocketAddress clientAddr = null;
                    try {
                        clientAddr = result.getRemoteAddress();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("收到新的连接：" + clientAddr);

                    if (assc.isOpen()){
                        assc.accept(null,this);
                    }

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    result.read(buffer, 2, new CompletionHandler<Integer, Integer>() {
                        @Override
                        public void completed(Integer result, Integer attachment) {
                            System.out.println("received the client: "+new String(buffer.array()));
                        }

                        @Override
                        public void failed(Throwable exc, Integer attachment) {
                            System.out.println(exc+"="+attachment);
                        }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
