package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.SensorRecord;


import java.util.Optional;
import java.util.UUID;


public interface SensorRecordRepository extends JpaRepository<SensorRecord, UUID> {
    Optional<SensorRecord> findById(UUID id);
}
