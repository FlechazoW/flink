package com.wtz.demo;

import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class KafkaCore {
    public static Properties buildKafkaConsumerProperties(String kafkaBootstrapServers) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return kafkaProperties;
    }

    public static DataStream<String> addKafkaSource(
            StreamExecutionEnvironment env, Properties kafkaProperties, String topic) {
        FlinkKafkaConsumer<String> kafkaSource =
                new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), kafkaProperties);
        // 设置消费最新数据
        kafkaSource.setStartFromLatest();
        return env.addSource(kafkaSource).name("kafkaSource");
    }

    public static Properties buildKafkaProducerProperties(String kafkaBootstrapServers) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return kafkaProperties;
    }

    public static void addKafkaSink(
            DataStream<String> source, Properties kafkaProducer, String topic) {
        FlinkKafkaProducer<String> kafkaSink =
                new FlinkKafkaProducer<>(
                        topic, (SerializationSchema<String>) String::getBytes, kafkaProducer);
        source.addSink(kafkaSink).name("kafkaSink");
    }
}
