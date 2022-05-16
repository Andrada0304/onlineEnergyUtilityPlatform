package ro.tuc.ds2020.dtos.builders;


import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.SensorRecordDTO;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SensorRecord;

public class SensorRecordBuilder {

    private SensorRecordBuilder(){

    }

    public static SensorRecordDTO toSensorRecordDTO(SensorRecord sensorRecord, Sensor sensor){
        return new SensorRecordDTO(
                sensorRecord.getId(),
                sensorRecord.getTimestamp(),
                sensorRecord.getEnergyConsumption(),
                SensorBuilder.toSensorDTO(sensor)
        );
    }
}
