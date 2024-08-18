package hitachi.smartpark.serviceimpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitachi.smartpark.entity.RegisterVehicle;
import hitachi.smartpark.model.Result;
import hitachi.smartpark.repository.VehicleRepository;
import hitachi.smartpark.service.VehicleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{
	
	@Autowired
	private final VehicleRepository vehicleRepository;
	
	public Result getVehiclesInParkingLot(String parkingLotId) {
		Result result = new Result();
	
	    try {
	        List<RegisterVehicle> vehicles = vehicleRepository.findByParkingLotId(parkingLotId);
	
	        if (vehicles.isEmpty()) {
	            result.setStatus("success");
	            result.setMessage("No vehicles currently parked in this lot");
	            result.setData(Collections.emptyList());
	        } else {
	            result.setStatus("success");
	            result.setMessage("Vehicles in the parking lot retrieved successfully");
	            result.setData(vehicles);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.setStatus("error");
	        result.setMessage("An error occurred while retrieving vehicles");
	        result.setData(null);
	    }
	
	    return result;
	}

}
