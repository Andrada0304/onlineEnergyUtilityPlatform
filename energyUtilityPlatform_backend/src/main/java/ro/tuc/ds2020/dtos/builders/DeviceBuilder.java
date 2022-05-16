package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceForClientDTO;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;

import java.util.List;
import java.util.stream.Collectors;

public class DeviceBuilder {
    private DeviceBuilder(){

    }

    public static DeviceDTO toDeviceDTO(Device device, Client client){
        return new DeviceDTO(
                device.getId(),
                device.getDescription(),
                device.getAddress(),
                device.getMaximumEnergyConsumption(),
                device.getAvgEnergyConsumption(),
                ClientBuilder.toClientDTO2(client)

        );
    }

   /*public static DeviceDTO toDeviceDTO(Device device, Client client){
        return new DeviceDTO(
                device.getId(),
                device.getDescription(),
                device.getMaximumEnergyConsumption(),
                device.getAvgEnergyConsumption(),
                ClientBuilder.toClientDTO2(client)

        );
    }*/

    public static DeviceForClientDTO toDeviceClientDTO(Device device)
    {
       /// Sensor sensor =device.getSensor();
        //List<Drug> drugs = treatment.getDrugs();
      /*  List<SensorDTO> drugDTOS =  drugs.stream()
                .map(DrugBuilder::entityToDTO)
                .collect(Collectors.toList());
*/
        return new DeviceForClientDTO(
                device.getId(),
                device.getDescription(),
                device.getMaximumEnergyConsumption(),
                device.getAvgEnergyConsumption(),
                null
        );
    }
}
