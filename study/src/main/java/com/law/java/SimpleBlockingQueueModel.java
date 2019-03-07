package com.law.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：生产者-消费者BlockingQueue模型简单版（代码量最少）
 * <p>
 * 问题：
 * 1、ArrayBlockingQueue 和 LinkedBlockingQueue区别？
 *
 * @author law
 * @create 2019-03-03 下午5:43
 */
public class SimpleBlockingQueueModel {
    private final AtomicInteger product = new AtomicInteger(0);
    private final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(100);

    class Producer extends Thread {
        public void run() {
            while (true) {
                int num = product.incrementAndGet();
                try {
                    Thread.sleep(1000);
                    blockingQueue.put(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "生产:" + num);
            }
        }
    }

    class Consumer extends Thread {
        public void run() {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + "消费:" + blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
        new SimpleBlockingQueueModel().run();
    }

}
