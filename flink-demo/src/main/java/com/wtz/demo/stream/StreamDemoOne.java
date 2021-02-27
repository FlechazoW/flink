package com.wtz.demo.stream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import com.wtz.demo.KafkaCore;

public class StreamDemoOne {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // source
        DataStream<String> source =
                KafkaCore.addKafkaSource(
                        env, KafkaCore.buildKafkaConsumerProperties("kudu1:9092"), "tiezhu_in");
        // sink
        KafkaCore.addKafkaSink(
                source, KafkaCore.buildKafkaProducerProperties("kudu1:9092"), "tiezhu_out");
        env.execute("Kafka stream Demo");
    }
}
