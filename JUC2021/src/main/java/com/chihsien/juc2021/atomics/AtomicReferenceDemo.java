package com.chihsien.juc2021.atomics;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;


@ToString
@AllArgsConstructor
@Getter
class User {
    String userName;
    int age;
}


/*** 
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 24);
        User li4 = new User("li4", 26);

        AtomicReference<User> atomicReferenceUser = new AtomicReference<>();

        atomicReferenceUser.set(z3);

        System.out.println(atomicReferenceUser.compareAndSet(z3, li4) + "\t" + atomicReferenceUser.get().toString());
        System.out.println(atomicReferenceUser.compareAndSet(z3, li4) + "\t" + atomicReferenceUser.get().toString());

    }
}
