package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRecordRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

  private final DeviceRepository deviceRepository;
    private final SensorRepository sensorRepository;
    private final SensorRecordRepository sensorRecordRepository;
    @Autowired
    public SensorService(DeviceRepository deviceRepository, SensorRecordRepository sensorRecordRepository, SensorRepository sensorRepository) {
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
        this.sensorRecordRepository = sensorRecordRepository;
    }


    public SensorDTO findSensorById (UUID deviceId)
    {
        Optional<Sensor> sensor = sensorRepository.findById(deviceId);

        return SensorBuilder.toSensorDTO(sensor.get());
    }

    public SensorDTO insertSensor(SensorDTO sensorDTO,UUID deviceId) {
        Device device = deviceRepository.findById(deviceId).orElse(null);

        Sensor sensor= new Sensor(
                sensorDTO.getSensorDescription(),
                sensorDTO.getMaximumValue(),
                sensorDTO.getRecords(),
                device
        );


        Sensor insertedSensor = sensorRepository.save(sensor);

        return SensorBuilder.toSensorDTO(insertedSensor);
    }

    public List<SensorDTO> findSensors()
    {
        List<Sensor> sensors = sensorRepository.findAll();
        return sensors.stream()
                .map(x -> SensorBuilder.toSensorDTO(x))
                .collect(Collectors.toList());
    }

    public SensorDTO updateSensor (UUID sensorId, SensorDTO sensorDTO) throws Exception {
        Sensor sensor = sensorRepository.findById(sensorId).orElse(null);

        if(sensor == null)
        {
            LOGGER.error("Person with id {} was not found in db", sensorId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + sensorId);
        }

        Sensor sensorToUpdate = new Sensor(sensor.getId(),sensorDTO.getSensorDescription(),
                sensorDTO.getMaximumValue(),
                sensorDTO.getRecords(),sensor.getDevice());

        return SensorBuilder.toSensorDTO(sensorRepository.save(sensorToUpdate));
    }
    public void deleteSensor (UUID sensorId) throws Exception
    {
      //  Sensor sensor = sensorRepository.findById(sensorId).orElse(null);
        //Device device = //sensor.getDevice();
     /*   Device device= new Device();
        for(Device dev:deviceRepository.findAll()){
            if(dev.getSensor().getId()==sensorId){
                device=dev;
            }
        }*/
     //   Client client = device.getClient();
      //  client.getDevices().remove(device);

     /*   List<SensorRecord> records = sensor.getRecords();
        if(records != null) {
            for (SensorRecord record: records) {
                sensorRecordRepository.delete(record);
            }
        }*/
     /*   if(device!=null) {
            deviceRepository.deleteById(device.getId());
        }
*/
        //sensorService.delete(sensorId);
        sensorRepository.deleteById(sensorId);
    }
}
