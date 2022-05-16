package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Account;

import java.util.UUID;

public class AdministratorViewDTO extends RepresentationModel<AdministratorViewDTO> {
    private UUID id;
    private String name;
    private String username ;

    public AdministratorViewDTO(){

    }

    public AdministratorViewDTO(UUID id, String name, String username){
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
