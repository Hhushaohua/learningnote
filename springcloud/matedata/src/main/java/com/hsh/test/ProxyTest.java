package com.hsh.test;

import com.hsh.model.Parts;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuShaohua on 2018/9/11.
 */
@RestController
public class ProxyTest {
    @GetMapping("/proxyTest")
    public String test(){
    System.out.println("我是基础数据服务的接口！");
    return "我是基础数据服务的接口！";
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/rabbitTest")
    public void rabbitTest(){
        amqpTemplate.convertAndSend("test","now");
    }
    @GetMapping("/exangeRabbitMqTest")
    public void exangeRabbitMqTest(){
        amqpTemplate.convertAndSend("myGoodsTest","goods","商品数据");
        amqpTemplate.convertAndSend("myGoodsTest","fruit","水果数据");
    }

    //传递对象信息到procument
    @GetMapping("/partTest")
    public void partTest(){
        Parts part=new Parts();
        part.setCode("qwe123");
        part.setName("part");
        amqpTemplate.convertAndSend("partsMQ","partkey",part);
    }

    //procument接收到对象,返回信息
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("partsmq"),
            key = "order",
            value = @Queue("Order")

    ))
    public void getReturn(String msg){
        System.out.println("接收返回回来的消息"+msg);
    }

}
