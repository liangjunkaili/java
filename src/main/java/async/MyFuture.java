package async;

import java.util.concurrent.*;

/**
 * @ClassName MyFuture
 * @Description TODO
 * @Author junliang
 * @Date 2019/11/28 6:05 PM
 * @Version 1.0
 **/
public class MyFuture{
    public static void main(String[] args) {
        try {
            new App().showSearch("liangjun");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
interface ArchiveSearcher {
    String search(String target);
}
class ArchiveSearcherImpl implements ArchiveSearcher{

    @Override
    public String search(String target) {
        return "ArchiveSearcher"+target;
    }
}
class App {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    ArchiveSearcher searcher = new ArchiveSearcherImpl();
    void showSearch(final String target) throws InterruptedException {
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() {
                return searcher.search(target);
            }
        }
        );
        displayOtherThings();
         try {
             System.out.println(future.isCancelled()+"=="+future.isDone());
             System.out.println(future.get());
         } catch (ExecutionException ex) {
             return;
         }
    }
     private void displayOtherThings(){
         System.out.println("displayOtherThings");
     }
}
