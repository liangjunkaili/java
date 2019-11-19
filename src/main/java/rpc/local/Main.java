package rpc.local;

import rpc.HelloService;

public class Main {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        helloService.hello("local");
    }
}
