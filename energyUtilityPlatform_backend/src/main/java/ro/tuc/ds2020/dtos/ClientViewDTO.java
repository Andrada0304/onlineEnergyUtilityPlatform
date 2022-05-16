package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;
import ro.tuc.ds2020.entities.Account;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class ClientViewDTO {

    private UUID id;
    private String name;
    private Date birthDate;
    private String address;
    private String username;
    private List<DeviceForClientDTO> clients;
    public ClientViewDTO(){

    }

    public ClientViewDTO(UUID id , String name, Date birthDate,String address,String username,List<DeviceForClientDTO> clients){
        this.id = id;
        this.name=name;
        this.birthDate=birthDate;
        this.address=address;
        this.username=username;
        this.clients=clients;
    }

    public ClientViewDTO(UUID id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
