package org.tony.flink.demo.stream.source;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.tony.flink.demo.beans.SensorReading;

import java.util.Arrays;

public class SourceTest_Collection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<SensorReading> source = env.fromCollection(Arrays.asList(
                new SensorReading("1", 1627367468910L, 22.34),
                new SensorReading("2",1627367258910L, 32.34),
                new SensorReading("3", 1627367278910L, 42.34),
                new SensorReading("4", 1627367288910L, 52.34)
        ));

//        source.flatMap(new Count()).keyBy("id");

        source.print();

        env.execute();
    }

    public static class Count implements FlatMapFunction<SensorReading, Double> {
        @Override
        public void flatMap(SensorReading value, Collector<Double> out) throws Exception {
            Integer a = 1;
        }
    }
}