package com.chihsien.juc2021.cas;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.get());


        System.out.println(atomicInteger.compareAndSet(5, 308) + "\t" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 3333) + "\t" + atomicInteger.get());
    }
}
