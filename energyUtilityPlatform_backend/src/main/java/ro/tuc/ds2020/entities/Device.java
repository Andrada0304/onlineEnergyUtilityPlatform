package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Device  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "description", length = 500)
    private String description ;

    @Column(name = "address", length = 100)
    private String address ;

    @Column(name = "maximum_energy_consumption")
    private float maximumEnergyConsumption ;

    @Column(name = "average_energy_consumption")
    private float avgEnergyConsumption ;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

   /* @ManyToMany(mappedBy = "devices", fetch = FetchType.EAGER)
    private List<Client> clients = new ArrayList<>();
*/
    // @ManyToOne(fetch = FetchType.LAZY)
    // private Sensor sensor;

   // @OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "sonsor_id", referencedColumnName = "id")
   // private Sensor sensor;
 /*  @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "sensor_id")
   private Sensor sensor;*/
    public Device(){

    }

    public Device(String description,String address, float maximumEnergyConsumtpion, float avgEnergyConsumption, Client client, Sensor sensor){
        this.description=description;
        this.address=address;
        this.maximumEnergyConsumption=maximumEnergyConsumtpion;
        this.avgEnergyConsumption=avgEnergyConsumption;
        this.client=client;
       // this.sensor=sensor;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
/*
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
*/


}
