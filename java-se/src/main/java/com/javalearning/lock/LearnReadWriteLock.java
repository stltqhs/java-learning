package com.javalearning.lock;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yuqing
 * @date 2018-08-22
 */
public class LearnReadWriteLock {

    public static void main(String args[]) {
        final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        final BlockingQueue queue = new ArrayBlockingQueue(10);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                readLock.lock();
                putValue(queue, 3);
                waitValue(queue, 1);
                readLock.unlock();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                waitValue(queue, 2);
                putValue(queue, 4);
                readLock.lock();
                readLock.unlock();
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                waitValue(queue, 3);
                putValue(queue, 4);
                writeLock.lock();
                writeLock.unlock();
            }
        };
        Thread t4 = new Thread() {
            @Override
            public void run() {
                waitValue(queue, 4);
                while (!Objects.equals(t3.getState(), State.WAITING));
                putValue(queue, 2);
                waitValue(queue, 4);
                while (!Objects.equals(t2.getState(), State.WAITING));
                putValue(queue, 1);
            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static void waitValue(BlockingQueue queue, int value) {
        Integer i = null;
        try {
            while ((i = (Integer) queue.take()) != value) {
                queue.put(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void putValue(BlockingQueue queue, int value) {
        try {
            queue.put(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
