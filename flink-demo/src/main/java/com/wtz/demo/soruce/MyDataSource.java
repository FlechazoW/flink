package com.wtz.demo.soruce;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import com.wtz.demo.data.DataEntity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class MyDataSource extends RichParallelSourceFunction<DataEntity> {

    private boolean running = true;

    @Override
    public void run(SourceContext<DataEntity> ctx) throws Exception {
        int id = 0;

        while (running) {
            Timestamp birth = new Timestamp(System.currentTimeMillis());
            ctx.collect(
                    new DataEntity(
                            id++,
                            "tiezhu",
                            birth,
                            new Time(birth.getTime()),
                            new Date(System.currentTimeMillis())));
            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        this.running = false;
    }
}
