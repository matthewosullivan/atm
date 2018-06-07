package atm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import atm.entity.AtmDevice;

@Repository
public interface AtmDeviceRepository extends CrudRepository<AtmDevice, Long> {
}