package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Sensor;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

public class DeviceDTO {

    private UUID id;
    private String description ;
    private String address ;
    private float maximumEnergyConsumption ;
    private float avgEnergyConsumption ;

    private ClientDTO clientDTO;
    public DeviceDTO(){

    }

    public DeviceDTO(UUID id,String description,String address, float maximumEnergyConsumtpion, float avgEnergyConsumption,ClientDTO clientDTO){
        this.id = id;
        this.description=description;
        this.address=address;
        this.maximumEnergyConsumption=maximumEnergyConsumtpion;
        this.avgEnergyConsumption=avgEnergyConsumption;
        this.clientDTO = clientDTO;
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


    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
