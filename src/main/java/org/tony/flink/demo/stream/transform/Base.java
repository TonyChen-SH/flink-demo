package org.tony.flink.demo.stream.transform;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Base {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        String path = "C:\\Users\\admin\\work-ayi\\github\\flink-demo\\src\\main\\resources\\hello.txt";
        DataStream<String> inputData = env.readTextFile(path);

        


    }
}
