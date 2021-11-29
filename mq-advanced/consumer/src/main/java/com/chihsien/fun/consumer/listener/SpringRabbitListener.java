package com.chihsien.fun.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        log.debug("消费者接收到simple.queue的消息：【" + msg + "】");
        System.out.println(1 / 0);
        log.info("消费者处理消息成功！");
    }

    /***
     * @describe
     * ttl的延迟队列
     * @param: msg
     * @return void
     * @author ChiHsien<br>
     * @version
     */
    @RabbitListener(bindings = @QueueBinding(
                    value = @Queue(name = "dl.queue", durable = "true"),
                    exchange = @Exchange(name = "dl.direct"),
                    key = "dl"
    ))
    public void listenDlQueue(String msg) {
        log.info("消费者接收到了dl.queue的延迟消息");
    }

    /***
     * @describe
     * 基于插件的延迟队列
     * @param: msg
     * @return void
     * @author ChiHsien<br>
     * @version
     */
    @RabbitListener(bindings = @QueueBinding(
                    value = @Queue(name = "delay.queue", durable = "true"),
                    exchange = @Exchange(name = "delay.direct", delayed = "true"),
                    key = "delay"
                                    ))
    public void listenDelayExchange(String msg) {
        log.info("消费者接收到了delay.queue的延迟消息");
    }
}
