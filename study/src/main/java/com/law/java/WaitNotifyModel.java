package com.law.java;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：生产者-消费者.
 * 参考：http://www.importnew.com/27063.html
 *
 * @author law
 * @create 2019-03-03 下午5:57
 */
public class WaitNotifyModel {
    private final Object LOCK = new Object();
    private final int BUFFER_SIZE = 100;
    private final Queue<Integer> queue = new LinkedList<>();
    private final AtomicInteger product = new AtomicInteger(0);

    class Producer extends Thread {
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    while (queue.size() == BUFFER_SIZE) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = product.incrementAndGet();
                    queue.offer(num);
                    System.out.println("producer-" + Thread.currentThread().getName() + "-" + queue.size() + "-" + num);
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer extends Thread {
        public void run() {
            while (true) {
                synchronized (LOCK) {
                    while (queue.size() == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("consumer-" + Thread.currentThread().getName() + "-" + queue.size() + "-" + queue.poll());
                    LOCK.notifyAll();
                }

            }
        }
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            new Consumer().start();
        }
        for (int i = 1; i <= 1; i++) {
            new Producer().start();
        }
    }

    public static void main(String[] args) {
        new WaitNotifyModel().run();
    }
}