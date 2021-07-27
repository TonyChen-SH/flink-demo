package org.tony.flink.demo.beans;

import lombok.Data;

// 传感器温度读数的数据类型
@Data
public class SensorReading {
    // 属性
    private String id;

    // 时间戳
    private Long timestamp;

    // 温度
    private Double temperature;

    public SensorReading() { }

    public SensorReading(String id, Long timestamp, Double temperature) {
        this.id = id;
        this.timestamp = timestamp;
        this.temperature = temperature;
    }
}
