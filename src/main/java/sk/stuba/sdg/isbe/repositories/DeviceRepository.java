package sk.stuba.sdg.isbe.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sk.stuba.sdg.isbe.domain.model.Device;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    Device findDeviceByMac(String macAddress);

    Optional<Device> getDeviceByUidAndDeactivated(String deviceId, boolean deactivated);

    @Query(value = "{ 'deactivated': ?0 }", fields = "{ 'dataPointTags' : 0 }")
    List<Device> getDevicesByDeactivatedWithoutDataPointTags(boolean deactivated);

    List<Device> getDevicesByDeactivated(boolean deactivated);
}
