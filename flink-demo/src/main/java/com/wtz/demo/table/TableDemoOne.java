package com.wtz.demo.table;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import com.wtz.demo.KafkaCore;

public class TableDemoOne {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings =
                EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();

        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(env, settings);

        DataStream<String> source =
                KafkaCore.addKafkaSource(
                        env, KafkaCore.buildKafkaConsumerProperties("kudu1:9092"), "tiezhu_in");

        tableEnvironment.executeSql(
                "CREATE TABLE printTable ("
                        + "id int, "
                        + "name string,"
                        + "age bigint, "
                        + "todayTime time, "
                        + "todayDate date, "
                        + "birth timestamp )"
                        + "with "
                        + "('connector' = 'print'");

        tableEnvironment.createTemporaryView("source", source);

        tableEnvironment.executeSql(
                "INSERT INTO printTable"
                        + "SELECT id, name, age, todayTime, todayDate, birth "
                        + "FROM source");

        env.execute("Table demo One");
    }
}
