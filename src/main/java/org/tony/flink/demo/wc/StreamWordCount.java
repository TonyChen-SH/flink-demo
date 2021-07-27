package org.tony.flink.demo.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 从文件中读取数据
        String path = "C:\\Users\\admin\\work-ayi\\github\\flink-demo\\src\\main\\resources\\hello.txt";
        DataStream<String> inputData = env.readTextFile(path);

        // 对数据集进行处理，按空格分词展开，转换成(word,1) 二元组进行统计
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = inputData.flatMap(new WordCount.SplitCount())
                .keyBy(0) // 按照第一个位置的word分组
                .sum(1);// 将第二个位置上的数据求和

        sum.print();

        // 执行任务
        env.execute();
    }
}
