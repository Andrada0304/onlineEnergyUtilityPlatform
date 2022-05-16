package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.entities.Sensor;

public class SensorBuilder {
    private SensorBuilder(){

    }

    public static SensorDTO toSensorDTO(Sensor sensor){
        return new SensorDTO(
                sensor.getId(),
                sensor.getSensorDescription(),
                sensor.getMaximumValue(),
                DeviceBuilder.toDeviceDTO(sensor.getDevice(), sensor.getDevice().getClient())
        );
    }
}
