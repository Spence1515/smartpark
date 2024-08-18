package hitachi.smartpark.service;

import hitachi.smartpark.model.RegisterVehicleRequest;
import hitachi.smartpark.model.Result;

public interface RegisterVehicleService {
	Result registerVehicle(RegisterVehicleRequest request); 
}
