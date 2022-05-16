package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Sensor;

import java.util.UUID;

public class DeviceForClientDTO {


    private UUID id;
    private String description ;
    private String address ;
    private float maximumEnergyConsumption ;
    private float avgEnergyConsumption ;
    private Sensor sensor;
    public DeviceForClientDTO(){

    }

    public DeviceForClientDTO(UUID id,String description, float maximumEnergyConsumtpion, float avgEnergyConsumption,Sensor sensor){
        this.id = id;
        this.description=description;
        this.maximumEnergyConsumption=maximumEnergyConsumtpion;
        this.avgEnergyConsumption=avgEnergyConsumption;
        this.sensor=sensor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMaximumEnergyConsumption() {
        return maximumEnergyConsumption;
    }

    public void setMaximumEnergyConsumption(float maximumEnergyConsumption) {
        this.maximumEnergyConsumption = maximumEnergyConsumption;
    }

    public float getAvgEnergyConsumption() {
        return avgEnergyConsumption;
    }

    public void setAvgEnergyConsumption(float avgEnergyConsumption) {
        this.avgEnergyConsumption = avgEnergyConsumption;
    }
}
