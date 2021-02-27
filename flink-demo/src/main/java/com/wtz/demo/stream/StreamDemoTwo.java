package com.wtz.demo.stream;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;

import com.wtz.demo.data.DataEntity;
import com.wtz.demo.soruce.MyDataSource;

public class StreamDemoTwo {
    public static void main(String[] args) throws Exception {
        executeLocalJob();
    }

    public static void executeLocalJob() throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // add source
        DataStreamSource<DataEntity> source = env.addSource(new MyDataSource());

        // 添加filter算子
        source.filter(DataEntity::getSex);

        source.addSink(new PrintSinkFunction<>());

        // 构建的DAG应该是 source -> filter -> sink
        env.execute("Stream Demo Two");
    }
}
