package hitachi.smartpark.service;

import hitachi.smartpark.model.Result;

public interface VehicleService {
	Result getVehiclesInParkingLot(String parkingLotId);
}
