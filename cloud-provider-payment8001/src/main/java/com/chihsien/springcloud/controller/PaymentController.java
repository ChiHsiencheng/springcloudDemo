package com.chihsien.springcloud.controller;

import com.chihsien.springcloud.entities.CommonResult;
import com.chihsien.springcloud.entities.Payment;
import com.chihsien.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther chihsien on 2021/5/27
 */
@RestController
@Slf4j
public class PaymentController {
    //读取yml配置中的server.port字段中的端口号配置 告知调用者用的集群中的哪个
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentService paymentService;
    //服务发现 对外提供该微服务的信息 是springframework.cloud.client.discovery 不是网飞那个
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result+"\t"+"哈哈哈哈");

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:"+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}", payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:"+serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        //所有在Eureka注册的服务信息
    List<String> services = discoveryClient.getServices();
        //遍历服务清单列表
        for (String element : services) {
            log.info("******element:"+element);
        }
        //serviceId:具体暴露的某个服务的id(8001 8002)
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;

    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}

