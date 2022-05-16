package ro.tuc.ds2020.rabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueueMessage {

    private UUID sensor_id;

    private Date timestamp;

    private float measurement_value;

    public UUID getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(UUID sensor_id) {
        this.sensor_id = sensor_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public float getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(float measurement_value) {
        this.measurement_value = measurement_value;
    }


}
