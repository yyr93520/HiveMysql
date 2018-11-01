package com.ict.kafka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ict.utils.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

class Consumer {

	private KafkaConsumer<String, String> consumer;
	private java.util.Properties props;

	public Consumer() {
		
		props = new java.util.Properties();
        props.put("zookeeper.connect", Properties.zkConnect);
        props.put("group.id", Properties.groupId);
        props.put("zookeeper.connection.timeout.ms", Properties.zkTimeout);
        props.put("consumer.timeout.ms", Properties.csTimeout);
		props.put("auto.commit.enable", Properties.commitEnable);
		props.put("auto.commit.interval.ms", Properties.commitInterval);
		props.put("auto.offset.reset", Properties.offsetReset);
		props.put("bootstrap.servers", Properties.bkConnect);
		//props.put("max.poll.records", Properties.maxRecords);
		//props.put("session.timeout.ms", Properties.sessionTimeout);
        //props.put("request.timeout.ms", "40000");

        //一次从kafka中poll出来的数据条数
        //max.poll.records条数据需要在在session.timeout.ms这个时间内处理完
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		createConsumer(Properties.topic);
		
	}
	
	private void createConsumer(String topic) {
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(topic));
	}

	public void closeConsumer() {
		consumer.close();
	}
	
	public List<String> getConsume() {
		
		List<String> values = new ArrayList<>();
		ConsumerRecords<String, String> records = consumer.poll(100);
			
		System.out.println("records.count():"+records.count());
			
		for (ConsumerRecord<String, String> record : records) {
			if (record != null) {
				values.add(record.value());
			}
		}
		consumer.commitAsync();
        return values;
    }  
}

