package sk.stuba.sdg.isbe.services;

import org.springframework.http.ResponseEntity;
import sk.stuba.sdg.isbe.domain.model.Device;
import sk.stuba.sdg.isbe.domain.model.Job;

import java.util.List;
import java.util.Map;

public interface DeviceService {
    Device createDevice(Device device);

    Device initializeDevice(String macAddress);

    Long initExpireTime(String deviceId);

    List<Device> getDevices();

    Device updateDevice(String deviceId, Device changeDevice);

    Device getDeviceById(String deviceId);

    ResponseEntity<Device> deleteDevice(String deviceId);

    ResponseEntity<Job> addJobToDevice(String deviceId, String jodId);

    Device addDataPointTagToDevice(String deviceId, String dataPointTagId);

    List<Job> getAllDeviceJobs(String deviceId);

    List<Job> getPendingDeviceJobs(String deviceId);

    List<Map<String, Map<String, List<Job>>>> getAllJobsInDevices();

    String getDeviceStatus(String deviceId);
}
