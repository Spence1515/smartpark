package hitachi.smartpark.serviceimpl;

import org.springframework.stereotype.Service;

import hitachi.smartpark.entity.RegisterVehicle;
import hitachi.smartpark.model.RegisterVehicleRequest;
import hitachi.smartpark.model.Result;
import hitachi.smartpark.repository.RegisterVehicleRepository;
import hitachi.smartpark.service.RegisterVehicleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterVehicleServiceImpl implements RegisterVehicleService{
	
	private final RegisterVehicleRepository registerVehicleRepository;
	
	public Result registerVehicle(RegisterVehicleRequest request) {
		Result result = new Result();
		
		try {
			RegisterVehicle vehicle = new RegisterVehicle();
			
			if (request.licensePlate().isEmpty() || request.ownersName().isEmpty() || request.type().isEmpty()) {
				result.setStatus("error");
				result.setMessage("Pls fill all");
				result.setData(null);
				
				return result;
			}
			
			if (registerVehicleRepository.existsByLicensePlate(request.licensePlate())) {
                result.setStatus("error");
                result.setMessage("License plate already exists");
                result.setData(null);
                return result;
            }
			
			
			if (!this.isValidLicensePlate(request.licensePlate())) {
                result.setStatus("error");
                result.setMessage("License plate can only contain letters, numbers, and dashes");
                result.setData(null);
                return result;
            }
			
			if (!this.isValidOwnersName(request.ownersName())) {
                result.setStatus("error");
                result.setMessage("Owner's name can only contain letters and spaces");
                result.setData(null);
                return result;
            }
			
			vehicle.setLicensePlate(request.licensePlate());
			vehicle.setOwnersName(request.ownersName());
			vehicle.setType(request.type());
			vehicle.setParkingLotId(null);
			
			registerVehicleRepository.save(vehicle);
			
			result.setStatus("success");
			result.setMessage("Parking lot successfully added");
			result.setData(vehicle);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("200");
			result.setMessage("error");
			result.setData(null);
			return result;
		}
		
	}
	
	public boolean isValidLicensePlate(String licensePlate) {
        String regex = "^[a-zA-Z0-9-]+$";
        return licensePlate != null && licensePlate.matches(regex);
    }
	
	public boolean isValidOwnersName(String ownersName) {
        String regex = "^[a-zA-Z ]+$";
        return ownersName != null && ownersName.matches(regex);
    }
	
}
