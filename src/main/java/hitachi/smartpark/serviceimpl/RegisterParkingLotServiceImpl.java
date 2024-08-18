package hitachi.smartpark.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hitachi.smartpark.entity.RegisterParkingLot;
import hitachi.smartpark.model.RegisterParkingLotRequest;
import hitachi.smartpark.model.Result;
import hitachi.smartpark.repository.RegisterParkingLotRepository;
import hitachi.smartpark.service.RegisterParkingLotService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterParkingLotServiceImpl implements RegisterParkingLotService{
	private static final Logger logger =   LoggerFactory.getLogger(RegisterParkingLotServiceImpl.class);
	
	private final RegisterParkingLotRepository registerParkingLotRepository;
	
	public Result registerParkingLot(RegisterParkingLotRequest request) {
		Result result = new Result();
		
		try {
			RegisterParkingLot parkingLot = new RegisterParkingLot();
			
			if (request.lotId().isEmpty()|| request.location().isEmpty()) {
				result.setStatus("error");
				result.setMessage("Pls fill lot id/location");
				result.setData(null);
				
				return result;
			}
			
			if (registerParkingLotRepository.existsByLotId(request.lotId())) {
                result.setStatus("error");
                result.setMessage("Parking Lot already exists");
                result.setData(null);
                return result;
            }
			
			if (!this.maxFiftyChar(request.lotId())) {
				result.setStatus("error");
                result.setMessage("Long ID must not exceed 50 characters");
                result.setData(null);
                return result;
			}
			
			parkingLot.setLotId(request.lotId());
			parkingLot.setLocation(request.location());
			parkingLot.setCapacity(request.capacity());
			parkingLot.setOccupied(request.occupied());
			
			registerParkingLotRepository.save(parkingLot);
			
			result.setStatus("success");
			result.setMessage("Parking lot successfully added");
			result.setData(parkingLot);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus("200");
			result.setMessage("error");
			result.setData(null);
			return result;
		}
		
	}
	
	public boolean maxFiftyChar(String lotId) {
        // Check if the type is not null and length is at most 50 characters
        return lotId != null && lotId.length() <= 50;
    }
	
}
