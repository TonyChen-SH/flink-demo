package org.tony.flink.demo.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCount {
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 从文件中读取数据
        String path = "C:\\Users\\admin\\work-ayi\\github\\flink-demo\\src\\main\\resources\\hello.txt";
        DataSet<String> inputDataSet = env.readTextFile(path);

        // 对数据集进行处理，按空格分词展开，转换成(word,1) 二元组进行统计
        AggregateOperator<Tuple2<String, Integer>> sum = inputDataSet.flatMap(new SplitCount())
                .groupBy(0) // 按照第一个位置的word分组
                .sum(1);// 将第二个位置上的数据求和

        sum.print();
    }

    private static class SplitCount implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            String[] words = value.split(" ");
            for (String word : words) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
