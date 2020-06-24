package nio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {
    public static void main(String[] args) {
        try {
            AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();
            SocketAddress address = new InetSocketAddress("localhost", 9898);
//            asc.bind(address);
            Future<Void> future = asc.connect(new InetSocketAddress("localhost",9898));
            future.get();

            String txt = "abcdef";
            ByteBuffer buffer = ByteBuffer.wrap(txt.getBytes());
            asc.write(buffer, 1, new CompletionHandler<Integer, Integer>() {
                @Override
                public void completed(Integer result, Integer attachment) {
                    System.out.println(result+"="+attachment);
                }

                @Override
                public void failed(Throwable exc, Integer attachment) {
                    System.out.println(exc+"="+attachment);
                }
            });
//            asc.connect(address, null, new CompletionHandler<Void, Integer>() {
//                @Override
//                public void completed(Void result, Integer attachment) {
//
//                }
//                @Override
//                public void failed(Throwable exc, Integer attachment) {
//
//                }
//            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
