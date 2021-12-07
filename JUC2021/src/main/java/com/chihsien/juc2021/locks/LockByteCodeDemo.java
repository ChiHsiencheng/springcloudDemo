package com.chihsien.juc2021.locks;

/**
 * 从字节码角度分析synchronized实现
 *
 * @author chihsiencheng
 */
public class LockByteCodeDemo {
    final Object object = new Object();


    public void m1() {
        synchronized (object) {
            System.out.println("----------hello sync");
            throw new RuntimeException("----ex");
        }
    }

    /*public synchronized void m2()
    {

    }*/

    public static synchronized void m2() {

    }
}
