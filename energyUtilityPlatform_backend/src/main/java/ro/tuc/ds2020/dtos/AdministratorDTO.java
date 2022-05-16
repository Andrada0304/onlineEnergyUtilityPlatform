package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Roles;

import javax.persistence.Column;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class AdministratorDTO extends RepresentationModel<AdministratorDTO> {

    private UUID id;
    private String name;
    private String birthDate;
    private String address;
    private String username;
    private String password;
    private Roles userRole;
    public AdministratorDTO(){

    }

    public AdministratorDTO(UUID id , String name, String birthDate,String address,String username,String password){
        this.id = id;
        this.name=name;
        this.birthDate=birthDate;
        this.address=address;
        this.username = username;
        this.password = password;
        this.userRole=Roles.ADMINISTRATOR;
    }


    public AdministratorDTO(String name, String birthDate,String address,String username,String password){
        this.name=name;
        this.birthDate=birthDate;
        this.address=address;
        this.username = username;
        this.password = password;
        this.userRole=Roles.ADMINISTRATOR;
    }

    public AdministratorDTO(String name,String address){
        this.name=name;
        this.address=address;
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

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }


}
