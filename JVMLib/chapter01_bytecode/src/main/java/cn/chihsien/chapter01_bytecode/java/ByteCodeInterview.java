package cn.chihsien.chapter01_bytecode.java;

import org.junit.Test;

/***
 * @describe
 *
 * @return
 * @author ChiHsien<br>
 * @version
 */
public class ByteCodeInterview {
    //面试题： i++和++i有什么区别？
    @Test
    public void test1(){
        int i = 10;
        i++;
//        ++i;

        System.out.println(i);
    }

    @Test
    public void test2(){
        int i = 10;
        i = i++;
        System.out.println(i);
        //10
    }

    @Test
    public void test3(){
        int i = 2;
        i *= i++; //i = i * i++
        System.out.println(i);
        //4
    }

    @Test
    public void test4(){
        int k = 10;
        k = k + (k++) + (++k); //
        System.out.println(k);//32
    }

    //包装类对象的缓存问题
    @Test
    public void test5(){

        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);//true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);//false
        //缓存对象的字节码范围 -128~127 超过127就各自再New 对比地址自然不一样

        Boolean b1 = true;
        Boolean b2 = true;
        System.out.println(b1 == b2);//true
        //布尔类型直接就是两个对象
    }

    //String声明的字面量数据都放在字符串常量池中
    //jdk 6中字符串常量池存放在方法区（即永久代中）
    //jdk7 及以后字符串常量池存放在堆空间
    @Test
    public void test6(){
        String str = new String("hello") + new String("world");//堆
        str.intern();
        String str1 = "helloworld";//常量池
        System.out.println(str == str1);//false --> true (加上intern() 在str声明之前)

    }
}
