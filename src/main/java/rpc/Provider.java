package rpc;

import rpc.local.HelloServiceImpl;

public class Provider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        Rpc.export(service,8888);
    }
}
