package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.SensorRecord;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SensorDTO {
    private UUID id;
    private String sensorDescription ;
    private float maximumValue ;
    private List<SensorRecord> records = new ArrayList<>();
    private DeviceDTO deviceDTO;
    public SensorDTO(){

    }

    public SensorDTO(UUID id, String sensorDescription, float maximumValue,DeviceDTO deviceDTO){
        this.id = id;
        this.sensorDescription=sensorDescription;
        this.maximumValue=maximumValue;
        this.deviceDTO=deviceDTO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSensorDescription() {
        return sensorDescription;
    }

    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    public float getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(float maximumValue) {
        this.maximumValue = maximumValue;
    }

    public List<SensorRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<SensorRecord> records) {
        this.records = records;
    }

    public DeviceDTO getDeviceDTO() {
        return deviceDTO;
    }

    public void setDeviceDTO(DeviceDTO deviceDTO) {
        this.deviceDTO = deviceDTO;
    }

}
