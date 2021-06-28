package retry;

public class RetryTest implements Runnable{

    public boolean test(){
        System.out.println(Thread.currentThread().getName()+"------");
//        throw new RuntimeException("RuntimeException");
        return false;
    }

    @Override
    public void run() {
        try {
//            RetryConfig.retry().call(() -> {
//                return test();
//            });
        } catch (Exception exception) {
            System.out.println("发邮件");
        }
    }

    public static void main(String[] args) {
        RetryTest retryTest = new RetryTest();
//        for (int i=0;i<10;i++){
            new Thread(retryTest).start();
//        }
    }
}
