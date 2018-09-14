package com.hsh.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by HuShaohua on 2018/9/13.
 */
@Component
public class RabbitTest {
    @RabbitListener(queuesToDeclare = @Queue("test"))
    public void test(String a){

        System.out.println("rbbit接受欧到的消息"+a);
    }
}
