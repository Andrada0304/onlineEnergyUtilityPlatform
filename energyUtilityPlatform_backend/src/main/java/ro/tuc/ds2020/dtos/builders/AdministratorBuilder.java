package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Administrator;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;

import java.util.List;
import java.util.stream.Collectors;

public class AdministratorBuilder {

    private AdministratorBuilder(){

    }


    public static AdministratorDTO toAdministratorDTO(Administrator administrator){
        return new AdministratorDTO(
                administrator.getId(),
                administrator.getName(),
                administrator.getBirthDate(),
                administrator.getAddress(),
                administrator.getUsername(),
                administrator.getPassword()

        );
    }

    public static AdministratorDTO toAdministrator2(Administrator administrator)
    {

     /*   List<DeviceForClientDTO> deviceForClientDTOS =  devices.stream()
                .map(DeviceBuilder::toDeviceClientDTO)
                .collect(Collectors.toList());
*/
        return new AdministratorDTO(
                administrator.getId(),
                administrator.getName(),
                administrator.getBirthDate(),
                administrator.getAddress(),
                administrator.getUsername(),
                administrator.getPassword()
        );
    }
    public static AdministratorDTO toAdministratorDTOView (Administrator administrator)
    {

        return new AdministratorDTO(
                administrator.getId(),
                administrator.getName(),
                administrator.getBirthDate(),
                administrator.getAddress(),
                administrator.getUsername(),
                administrator.getPassword()
                //CaregiverBuilder.entityToDTOCaregiverPatient(patient.getCaregiver())
        );

    }


}
