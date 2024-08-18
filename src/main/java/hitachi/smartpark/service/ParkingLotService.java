package hitachi.smartpark.service;

import hitachi.smartpark.model.ParkingLotRequest;
import hitachi.smartpark.model.RegisterVehicleRequest;
import hitachi.smartpark.model.Result;

public interface ParkingLotService {
	Result checkInVehicle(ParkingLotRequest request);
	Result checkOutVehicle(ParkingLotRequest request);
	Result getAvailableParkingLots();
}
