package hitachi.smartpark.service;

import hitachi.smartpark.model.RegisterParkingLotRequest;
import hitachi.smartpark.model.Result;

public interface RegisterParkingLotService {
	Result registerParkingLot(RegisterParkingLotRequest request); 
}
