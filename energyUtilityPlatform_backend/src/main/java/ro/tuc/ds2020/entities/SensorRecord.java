package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class SensorRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "energy_consumption")
    private float energyConsumption;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sensor sensor;

    public SensorRecord(){

    }

    public SensorRecord(String timestamp,float energyConsumption,Sensor sensor ){
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
        this.sensor=sensor;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
