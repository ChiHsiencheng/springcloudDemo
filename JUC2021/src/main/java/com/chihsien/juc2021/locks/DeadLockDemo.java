package com.chihsien.juc2021.locks;

import java.util.concurrent.TimeUnit;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class DeadLockDemo {
    static Object lockA = new Object();
    static Object lockB = new Object();


    public static void main(String[] args) {

        Thread a = new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有A锁，期待获得B锁");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "\t 获得B锁成功");
                }
            }
        }, "a");
        a.start();

        new Thread(() -> {
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t" + " 自己持有B锁，期待获得A锁");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "\t 获得A锁成功");
                }
            }
        }, "b").start();


    }
}
