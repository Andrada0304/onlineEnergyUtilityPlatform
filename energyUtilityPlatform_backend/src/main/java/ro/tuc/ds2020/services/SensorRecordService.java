package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.SensorDTO;
import ro.tuc.ds2020.dtos.SensorRecordDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.dtos.builders.SensorRecordBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.SensorRecordRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorRecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final SensorRepository sensorRepository;
    private final SensorRecordRepository sensorRecordRepository;

    @Autowired
    public SensorRecordService(SensorRepository sensorRepository, SensorRecordRepository sensorRecordRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorRecordRepository = sensorRecordRepository;
    }

    public SensorRecordDTO findCounterById (UUID counterId)
    {
        Optional<SensorRecord> counter = sensorRecordRepository.findById(counterId);

        return SensorRecordBuilder.toSensorRecordDTO(counter.get(),counter.get().getSensor());
    }

    public SensorRecordDTO insertSensorRecord(SensorRecordDTO counterDTO, UUID sensorId) {

        Sensor sensor = sensorRepository.findById(sensorId).orElse(null);

        SensorRecord counter = new SensorRecord(
                counterDTO.getTimestamp(),
                counterDTO.getEnergyConsumption(),
                sensor);


        SensorRecord insertedCounter = sensorRecordRepository.save(counter);

        sensor.getRecords().add(insertedCounter);

        return SensorRecordBuilder.toSensorRecordDTO(insertedCounter,sensor);
    }

    public List<SensorRecordDTO> findSensorRecordList()
    {
        List<SensorRecord> sensorRecordList = sensorRecordRepository.findAll();
        return sensorRecordList.stream()
                .map(x -> SensorRecordBuilder.toSensorRecordDTO(x,x.getSensor()))
                .collect(Collectors.toList());
    }
/*

    public SensorRecordDTO updateSensorRecord (UUID sensorRecordId, SensorRecordDTO sensorRecordDTO) throws Exception {
        SensorRecord sensorRecord = sensorRecordRepository.findById(sensorRecordId).orElse(null);

        if(sensorRecord == null)
        {
            LOGGER.error("Person with id {} was not found in db", sensorRecordId);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + sensorRecordId);
        }

        SensorRecord sensorRecordToUpdate = new SensorRecord(sensorRecordDTO.getTimestamp(),sensorRecordDTO.getEnergyConsumption(),
                sensorRecordDTO.getSensorDTO());

        return SensorRecordBuilder.toSensorRecordDTO(sensorRecordRepository.save(sensorRecordToUpdate),sensorRecordToUpdate.getSensor());
    }
    public void deleteSensorRecord (UUID sensorRecordId) throws Exception
    {
        SensorRecord sensorRecord = sensorRecordRepository.findById(sensorRecordId).orElse(null);

        Sensor sensor = sensorRecord.getSensor();

       // Client client = device.getClient();
        sensor.getRecords().remove(sensor);
        sensorRecordRepository.deleteById(sensorRecordId);
        sensorRepository.deleteById(sensorRecord.getSensor().getId());

    }
*/
}
