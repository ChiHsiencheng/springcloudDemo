package com.chihsien.zookeeper.case2;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chihsiencheng
 */
public class DistributedLockTest {

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        ThreadPoolExecutor te = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
       final  DistributedLock lock1 = new DistributedLock();
       final  DistributedLock lock2 = new DistributedLock();

       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   lock1.zklock();
                   System.out.println("线程1 启动，获取到锁");
                   Thread.sleep(5 * 1000);

                   lock1.unZkLock();
                   System.out.println("线程1 释放锁");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock2.zklock();
                    System.out.println("线程2 启动，获取到锁");
                    Thread.sleep(5 * 1000);

                    lock2.unZkLock();
                    System.out.println("线程2 释放锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
