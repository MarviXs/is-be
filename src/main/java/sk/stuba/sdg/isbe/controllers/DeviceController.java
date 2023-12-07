package sk.stuba.sdg.isbe.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.sdg.isbe.domain.model.Device;
import sk.stuba.sdg.isbe.domain.model.Job;
import sk.stuba.sdg.isbe.domain.model.JobStatus;
import sk.stuba.sdg.isbe.domain.model.User;
import sk.stuba.sdg.isbe.services.DeviceService;
import sk.stuba.sdg.isbe.services.JobStatusService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private JobStatusService jobStatusService;

    @GetMapping
    public List<Device> getDevices() {
        return this.deviceService.getDevices();
    }

    @Operation(summary = "Add new device into the system")
    @PostMapping("/create")
    public Device createDevice(@Valid @RequestBody Device device) {
        return this.deviceService.createDevice(device);
    }

    @Operation(summary = "Initialize device by mac address in 1 min time window")
    @GetMapping(value = "/initializeDevice/{macAddress}")
    public Device initializeDevice(@PathVariable String macAddress) {
        return deviceService.initializeDevice(macAddress);
    }

    @Operation(summary = "Initialize time of device set to 1 min window")
    @GetMapping(value = "/initExpireTime/{deviceId}")
    public Long initExpireTime(@PathVariable String deviceId) {
        return deviceService.initExpireTime(deviceId);
    }

    @Operation(summary = "Update Device by ID using object")
    @PutMapping("updateDevice/{deviceId}")
    public Device updateDevice(@PathVariable String deviceId, @Valid @RequestBody Device changeDevice) {
        return deviceService.updateDevice(deviceId, changeDevice);
    }

    @Operation(summary = "Delete device by uid")
    @DeleteMapping("delete/{deviceId}")
    public ResponseEntity<Device> deleteDevice(@PathVariable String deviceId) {
        return this.deviceService.deleteDevice(deviceId);
    }

    @GetMapping("/getDeviceById/{deviceId}")
    public Device getDeviceById(@PathVariable String deviceId) {
        return this.deviceService.getDeviceById(deviceId);
    }

    @Operation(summary = "Add job to device base on id inset into list of jobs")
    @PutMapping("addJobToDevice/{deviceId}/{jobId}")
    public ResponseEntity<Job> addJobToDevice(@PathVariable String deviceId, @PathVariable String jobId) {
        return this.deviceService.addJobToDevice(deviceId, jobId);
    }

    @Operation(summary = "Add data point tag to device base on id inset into list of dataPointTags")
    @PutMapping("addDataPointTagToDevice/{deviceId}/{dataPointTagId}")
    public Device addDataPointTagToDevice(@PathVariable String deviceId, @PathVariable String dataPointTagId) {
        return this.deviceService.addDataPointTagToDevice(deviceId, dataPointTagId);
    }

    @Operation(summary = "Json of all jobs assigned to device, if second parameter set to true only pending get")
    @GetMapping(value = "/getAllDeviceJobs/{deviceId}/{pending}")
    public List<Job> getAllDeviceJobs(@PathVariable String deviceId, @PathVariable Boolean pending) {
        if (pending) {
            return this.deviceService.getPendingDeviceJobs(deviceId);
        }
        return this.deviceService.getAllDeviceJobs(deviceId);
    }

    @Operation(summary = "Get all jobs in devices")
    @GetMapping("/getAllJobsInDevices")
    public List<Map<String, Map<String, List<Job>>>> getAllJobsInDevices() {
        return this.deviceService.getAllJobsInDevices();
    }

    @Operation(summary = "Update JobStatus by device uid")
    @PostMapping("/updateJobStatus/{deviceId}/{jobStatusId}")
    public JobStatus updateJobStatus(@PathVariable String deviceId, @PathVariable String jobStatusId, @Valid @RequestBody JobStatus changeJobStatus) {
        deviceService.getDeviceById(deviceId);

        return jobStatusService.updateJobStatus(jobStatusId, changeJobStatus, deviceId);
    }

    @Operation(summary = "Add a shared user to a device")
    @PutMapping("/addSharedUser/{deviceId}/{userId}")
    public ResponseEntity<Device> addSharedUserToDevice(@PathVariable String deviceId, @PathVariable String userId) {
        Device updatedDevice = deviceService.addSharedUserToDevice(deviceId, userId);
        return ResponseEntity.ok(updatedDevice);
    }

    @Operation(summary = "Remove a shared user from a device")
    @DeleteMapping("/removeSharedUser/{deviceId}/{userId}")
    public ResponseEntity<Device> removeSharedUserFromDevice(@PathVariable String deviceId, @PathVariable String userId) {
        Device updatedDevice = deviceService.removeSharedUserFromDevice(deviceId, userId);
        return ResponseEntity.ok(updatedDevice);
    }

    @Operation(summary = "Get shared users of a device")
    @GetMapping("/getSharedUsers/{deviceId}")
    public ResponseEntity<List<User>> getSharedUsers(@PathVariable String deviceId) {
        List<User> sharedUsers = deviceService.getSharedUsers(deviceId);
        return ResponseEntity.ok(sharedUsers);
    }

    @Operation(summary = "Get devices shared with a specific user")
    @GetMapping("/getDevicesSharedWithUser/{userId}")
    public List<Device> getDevicesSharedWithUser(@PathVariable String userId) {
        return deviceService.getDevicesSharedWithUser(userId);
    }

    @Operation(summary = "Get status of the device")
    @GetMapping("getDeviceStatus/{deviceId}")
    public String getDeviceStatus(@PathVariable String deviceId) {
        return this.deviceService.getDeviceStatus(deviceId);
    }
}
