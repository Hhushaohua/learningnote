package com.hsh.rabbitMQ;


import com.hsh.model.Parts;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by HuShaohua on 2018/9/13.
 */
@Component
public class RabbitTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @RabbitListener(queuesToDeclare = @Queue("test"))
    public void test(String a){
        System.out.println("rbbit接受欧到的消息"+a);
    }


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("myMQTest"),
                    key = "fruit",
                    value = @Queue("testMQ")
            )
    )
    public void myMQTest(String msg){
        System.out.println("rbbit接受欧到的消息"+msg);
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("myGoodsTest"),
                    key = "goods",
                    value = @Queue("testMQ")
            )
    )
    public void myGoodsTest(String msg){
        System.out.println("rbbit接受欧到的消息"+msg);
    }
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("partsMQ"),
                    key = "partkey",
                    value = @Queue("partTest")
            )
    )
    public void partTest(Parts part){
        System.out.println("rbbit接收part"+part);
        amqpTemplate.convertAndSend("partsmq","order",part.getCode());
    }

}
