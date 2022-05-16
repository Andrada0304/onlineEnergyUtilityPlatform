package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;


    @Column(name = "sensor_description", length = 500)
    private String sensorDescription ;

    @Column(name = "maximum_value")
    private float maximumValue ;

   // @OneToOne(mappedBy = "device")
   // private Device device;

    /* @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Device> devices = new ArrayList<>();
 */
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorRecord> records = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;
    public Sensor(){

    }

    public Sensor(String sensorDescription, float maximumValue, List<SensorRecord> records,Device device){
        this.sensorDescription=sensorDescription;
        this.maximumValue=maximumValue;
        this.device=device;
        this.records=records;
    }


    public Sensor(UUID id,String sensorDescription, float maximumValue, List<SensorRecord> records,Device device){
        this.id = id;
        this.sensorDescription=sensorDescription;
        this.maximumValue=maximumValue;
        this.device=device;
        this.records=records;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<SensorRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<SensorRecord> records) {
        this.records = records;
    }
}
