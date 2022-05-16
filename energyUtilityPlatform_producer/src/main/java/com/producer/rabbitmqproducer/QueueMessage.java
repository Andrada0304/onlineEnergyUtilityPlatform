package com.producer.rabbitmqproducer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueMessage {
    private Date timestamp;
    private UUID sensor_id;
    private double measurement_value;

    public QueueMessage(@JsonProperty("sensor_id")  UUID sensor_id,
                      @JsonProperty("timestamp") Date timestamp,
                      @JsonProperty("measurement_value") double measurement_value){
        this.sensor_id = sensor_id;
        this.timestamp = timestamp;
        this.measurement_value = measurement_value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public UUID getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(UUID sensor_id) {
        this.sensor_id = sensor_id;
    }

    public double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(float measurement_value) {
        this.measurement_value = measurement_value;
    }
    public  String toJSONString() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sensor_id",  this.sensor_id);
        jsonObject.put("timestamp", this.timestamp);
        jsonObject.put("measurement_value", this.measurement_value);
        return jsonObject.toString();
    }


}
