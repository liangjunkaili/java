package rpc;

public class Cosumer {
    public static void main(String[] args) throws Exception {
        HelloService service = Rpc.refer(HelloService.class,"127.0.0.1",8888);
        service.hello("12");
    }
}
