package com.law.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：
 *
 * @author law
 * @create 2019-03-03 下午12:09
 */
public class BlockQueueModel {

    private final BlockingQueue<Object> blockingQueue = new LinkedBlockingDeque<>(100);
    private final AtomicInteger product = new AtomicInteger(0);

    interface Producer {
        void produce();
    }

    interface Consumer {
        void consume();
    }

    class ProducerImpl implements Runnable, Producer {

        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void produce() {
            try {
                int a = product.incrementAndGet();
                blockingQueue.put(a);
                System.out.println(Thread.currentThread().getName() + "生产:" + a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class ConsumerImpl implements Runnable, Consumer {

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void consume() {
            try {
                System.out.println(Thread.currentThread().getName() + "消费:" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    ProducerImpl instanceProducer() {
        return new ProducerImpl();
    }

    ConsumerImpl instanceConsumer() {
        return new ConsumerImpl();
    }

    public static void main(String[] args) {
        BlockQueueModel model = new BlockQueueModel();
        for (int i = 1; i <= 1; i++) {
            new Thread(model.instanceProducer(), "生产者" + i).start();
        }
        for (int i = 1; i <= 1; i++) {
            new Thread(model.instanceConsumer(), "消费者" + i).start();
        }
    }
}
