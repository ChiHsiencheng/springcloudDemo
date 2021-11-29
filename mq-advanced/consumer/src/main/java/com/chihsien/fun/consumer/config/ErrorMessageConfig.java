package com.chihsien.fun.consumer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @describe
 *  RepublishMessageRecoverer：重试耗尽后，将失败消息投递到指定的交换机
 *  实际生产推荐这种方式
 * @return
 * @author ChiHsien<br>
 * @version
 */
@Configuration
public class ErrorMessageConfig {

    @Bean
    public DirectExchange errorMessageExchange(){
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue");
    }

    @Bean
    public Binding errorMessageBinding(){
        return BindingBuilder.bind(errorQueue()).to(errorMessageExchange()).with("error");
    }

    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        //RepublishMessageRecoverer：重试耗尽后，将失败消息投递到指定的交换机  实际生产时将error.direct error error.queue等定义成常量
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }
}
