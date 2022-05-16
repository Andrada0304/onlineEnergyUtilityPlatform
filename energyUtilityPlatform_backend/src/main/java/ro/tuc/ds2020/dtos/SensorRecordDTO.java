package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SensorRecord;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

public class SensorRecordDTO {

    private UUID id;
    private String timestamp;
    private float energyConsumption;
    private SensorDTO sensorDTO;

    public SensorRecordDTO(){

    }

    public SensorRecordDTO(UUID id,String timestamp,float energyConsumption){
        this.id = id;
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
    }

    public SensorRecordDTO(UUID id,String timestamp,float energyConsumption,SensorDTO sensorDTO){
        this.id = id;
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
        this.sensorDTO=sensorDTO;
    }

    public SensorRecordDTO(String timestamp,float energyConsumption,SensorDTO sensorDTO){
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
        this.sensorDTO=sensorDTO;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(float energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

}
