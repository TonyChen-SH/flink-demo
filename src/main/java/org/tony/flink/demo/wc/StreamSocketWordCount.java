package org.tony.flink.demo.wc;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamSocketWordCount {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 并行度设置为1
        env.setParallelism(1);

        // 用: nc -l -p 7777 启动一个模拟的socket服务器
        // 从socket流中读取数据(连接目标sokcet服务器. hostname: 目标服务器地址，port: 目标端口地址)
        DataStream<String> inputData = env.socketTextStream("127.0.0.1", 7777);

        // 从启动参数中提前配置文件
        // ParameterTool.fromArgs();

        // 对数据集进行处理，按空格分词展开，转换成(word,1) 二元组进行统计
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = inputData.flatMap(new WordCount.SplitCount())
                .keyBy(0) // 按照第一个位置的word分组
                .sum(1);// 将第二个位置上的数据求和

        sum.print();

        // 执行任务
        env.execute();
    }
}
