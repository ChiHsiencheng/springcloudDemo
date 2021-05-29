package com.chihsien.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @auther chihsien on 2021/5/28
 */
@Service
public class PaymentService {
    /**
     * 正常访问，一切OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id)
    {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     * @param id
     * @return
     *  HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler")
     *  该方法出现状况 就去找备用方案paymentInfo_TimeOutHandler
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            //设置自身调用超时时间峰值，峰值内可以正常运行 3s 超过了就去找兜底方法paymentInfo_TimeOutHandler作服务器降级fallback
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10/0;

        try { TimeUnit.SECONDS.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费3秒";
    }

    /**
     * @param: id
     * @return java.lang.String
     * @throws
     * @author ChiHsien<br>
     *     向调用方返回一个符合预期的、可处理的备选响应FallBack
     *     拒绝长时间等待或者抛出调用方法异常
     */

    public String paymentInfo_TimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用服务超时或异常：\t"+ id +"\t"+ "\t当前线程池名字" + Thread.currentThread().getName();

    }

    //=========服务熔断 10秒的时间窗口期内10次请求失败率达到60%就给你拉闸！
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸！
    })
    //主业务逻辑类 paymentCircuitBreaker
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            //模拟运行时异常
            throw new RuntimeException("******id 不能负数");
        }
        //IdUtil.simpleUUID() 等于 UUID.randomUUID().toString(); 不带“-” huTool工具包
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    //出事后的兜底方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }







}
