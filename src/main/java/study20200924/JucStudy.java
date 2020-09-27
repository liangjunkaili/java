package study20200924;

import study20200907.ThreadPool;

import java.util.concurrent.Semaphore;

public class JucStudy implements Runnable{
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

    }

    @Override
    public void run() {
        System.out.println("hahahahahhahaha");
    }
}
