package xyz.oleryu.mqs.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * OLERYU
 */
public class Producer {
    public static void main(String[] args) throws MQClientException,
            InterruptedException {

        DefaultMQProducer producer = new DefaultMQProducer("OleryGroupName");

        producer.setNamesrvAddr("192.168.227.142:9876");
        producer.setInstanceName("OleryPro");
        producer.setVipChannelEnabled(false);

        producer.start();

        for (int i = 0; i < 100; i ++) {
            try {
                Message msg = new Message("OLERY_PRO_TEST",// topic
                        "TagA",// tag
                        "MSG_SEQ_" + String.valueOf(i),// key
                        ("Hello MetaQ").getBytes());// body
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();
    }
}
