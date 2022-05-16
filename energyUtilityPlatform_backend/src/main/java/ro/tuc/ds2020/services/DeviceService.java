package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Client;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.ClientRepository;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final DeviceRepository deviceRepository;
    private final ClientRepository clientRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, ClientRepository clientRepository, SensorRepository sensorRepository) {
        this.deviceRepository = deviceRepository;
        this.clientRepository = clientRepository;
        this.sensorRepository = sensorRepository;
    }


    public DeviceDTO findDeviceById (UUID deviceId)
    {
        Optional<Device> device = deviceRepository.findById(deviceId);

        return DeviceBuilder.toDeviceDTO(device.get(), device.get().getClient());
    }

   /* public DeviceDTO insertDevice(DeviceDTO deviceDTO, UUID clientId) {

        Client client = clientRepository.findById(clientId).orElse(null);

        Device device = new Device(
                deviceDTO.getDescription(),
                deviceDTO.getMaximumEnergyConsumption(),
                deviceDTO.getAvgEnergyConsumption(),
                client, null);


        Device insertedDevice = deviceRepository.save(device);

        client.getDevices().add(insertedDevice);

        return DeviceBuilder.toDeviceDTO(insertedDevice,client);
    }*/


 /*   public DeviceDTO insertDevice(DeviceDTO deviceDTO) {

        ClientDTO clientDTO = deviceDTO.getClientDTO();
        UUID clientId = clientDTO.getId();
        Client client = clientRepository.findById(clientId).orElse(null);

        Device device = new Device(
                deviceDTO.getDescription(),
                deviceDTO.getMaximumEnergyConsumption(),
                deviceDTO.getAvgEnergyConsumption(),
                client, null);


        Device insertedDevice = deviceRepository.save(device);

        client.getDevices().add(insertedDevice);

        return DeviceBuilder.toDeviceDTO(insertedDevice,client);
    }*/

    public DeviceDTO insertDevice(DeviceDTO deviceDTO, UUID clientId) {

        Client client = clientRepository.findById(clientId).orElse(null);

        Device device = new Device(
                deviceDTO.getDescription(),
                deviceDTO.getAddress(),
                deviceDTO.getMaximumEnergyConsumption(),
                deviceDTO.getAvgEnergyConsumption(),
                client, null);


        Device insertedDevice = deviceRepository.save(device);

        client.getDevices().add(insertedDevice);

        return DeviceBuilder.toDeviceDTO(insertedDevice,client);
    }
    public List<DeviceDTO> findDevices()
    {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream()
                .map(x -> DeviceBuilder.toDeviceDTO(x,x.getClient()))
                .collect(Collectors.toList());
    }


    public DeviceDTO updateDevice (UUID deviceId, DeviceDTO deviceDTO) throws Exception {
        Device device = deviceRepository.findById(deviceId).orElse(null);

        if(device == null)
        {
            LOGGER.error("Person with id {} was not found in db", deviceId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + deviceId);
        }

        Device deviceToUpdate = new Device(deviceDTO.getDescription(),deviceDTO.getAddress(),
                deviceDTO.getMaximumEnergyConsumption(), deviceDTO.getAvgEnergyConsumption(),
                device.getClient(),null);

        return DeviceBuilder.toDeviceDTO(deviceRepository.save(deviceToUpdate),deviceToUpdate.getClient());
    }
    public void deleteDevice (UUID deviceId) throws Exception
    {
        Device device = deviceRepository.findById(deviceId).orElse(null);

      //  Sensor sensor = device.getSensor();

        Client client = device.getClient();
        client.getDevices().remove(device);
        deviceRepository.deleteById(deviceId);
      //  sensorRepository.deleteById(device.getSensor().getId());

    }
}
