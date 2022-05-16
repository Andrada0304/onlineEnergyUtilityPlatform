package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.AdministratorViewDTO;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientViewDTO;
import ro.tuc.ds2020.dtos.DeviceForClientDTO;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Administrator;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;

import java.util.List;
import java.util.stream.Collectors;

public class ClientBuilder {

    private ClientBuilder(){

    }


    public static ClientDTO toClientDTO(Client client){
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getBirthDate(),
                client.getAddress(),
                client.getUsername(),
                client.getPassword(),
                client.getDevices()

        );
    }

    public static ClientDTO toClientDTO2(Client client)
    {

        List<Device> devices = client.getDevices();
        if(devices == null){
            return new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getBirthDate(),
                    client.getAddress(),
                    client.getUsername(),
                    client.getPassword(),
                    null);

        }
     /*   List<DeviceForClientDTO> deviceForClientDTOS =  devices.stream()
                .map(DeviceBuilder::toDeviceClientDTO)
                .collect(Collectors.toList());
*/
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getBirthDate(),
                client.getAddress(),
                client.getUsername(),
                client.getPassword(),
                devices
        );
    }
    public static ClientDTO toClientDTOView (Client client)
    {
        List<Device> devices = client.getDevices();
        List<DeviceForClientDTO> deviceForClientDTOS = devices.stream()
                .map(DeviceBuilder::toDeviceClientDTO)
                .collect(Collectors.toList());


        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getBirthDate(),
                client.getAddress(),
                client.getUsername(),
                client.getPassword(),
                devices
                //CaregiverBuilder.entityToDTOCaregiverPatient(patient.getCaregiver())
        );

    }


}
