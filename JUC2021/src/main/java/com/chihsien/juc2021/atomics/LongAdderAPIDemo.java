package com.chihsien.juc2021.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();//只能做加法

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue());

        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left - right;
            }
        }, 100);

        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(2);//3
        longAccumulator.accumulate(3);//6

        System.out.println(longAccumulator.longValue());


    }
}
