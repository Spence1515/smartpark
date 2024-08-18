package hitachi.smartpark.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitachi.smartpark.entity.RegisterParkingLot;
import hitachi.smartpark.entity.RegisterVehicle;
import hitachi.smartpark.model.ParkingLotRequest;
import hitachi.smartpark.model.Result;
import hitachi.smartpark.repository.AvailableParkingLot;
import hitachi.smartpark.repository.RegisterParkingLotRepository;
import hitachi.smartpark.repository.RegisterVehicleRepository;
import hitachi.smartpark.service.ParkingLotService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingLotServiceImpl implements ParkingLotService{
	@Autowired
    private RegisterParkingLotRepository parkingLotRepository;

    @Autowired
    private RegisterVehicleRepository vehicleRepository;
    
    @Autowired
    private AvailableParkingLot availableParkingLot;
    
    public Result checkInVehicle(ParkingLotRequest request) {
        Result result = new Result();

        try {
        	Optional<RegisterVehicle> optionalVehicle = vehicleRepository.findByLicensePlate(request.licensePlate());
        	RegisterParkingLot parkingLot = parkingLotRepository.findByLotId(request.lotId());
            
        	 if (optionalVehicle.isEmpty()) {
                 result.setStatus("error");
                 result.setMessage("Vehicle not found");
                 result.setData(null);
                 return result;
             }
        	 
        	 if (parkingLot == null) {
                 result.setStatus("error");
                 result.setMessage("Parking lot not found");
                 result.setData(null);
                 return result;
             }
        	 
            
        	 RegisterVehicle vehicle = optionalVehicle.get();
        	 
        	 if (vehicle.getParkingLotId() != null) {
                 result.setStatus("error");
                 result.setMessage("Vehicle already assigned to a parking lot");
                 result.setData(null);
                 return result;
             }
        	 
        	 if (parkingLot.getOccupied() >= parkingLot.getCapacity()) {
                 result.setStatus("error");
                 result.setMessage("Parking lot is full");
                 result.setData(null);
                 return result;
             }
        	 
             vehicle.setParkingLotId(request.lotId());
             vehicleRepository.save(vehicle);
             
             parkingLot.setOccupied(parkingLot.getOccupied() + 1);
             parkingLotRepository.save(parkingLot);
             
             result.setStatus("success");
             result.setMessage("Vehicle successfully checked in");
             result.setData(vehicle);

            return result;
        } catch (IllegalStateException e) {
            result.setStatus("error");
            result.setMessage(e.getMessage());
            result.setData(null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
            result.setMessage("An error occurred");
            result.setData(null);
            return result;
        }
    }
    
    public Result checkOutVehicle(ParkingLotRequest request) {
        Result result = new Result();

        try {
            // Find the vehicle by license plate
            Optional<RegisterVehicle> optionalVehicle = vehicleRepository.findByLicensePlate(request.licensePlate());

            // Find the parking lot by lotId
            RegisterParkingLot parkingLot = parkingLotRepository.findByLotId(request.lotId());

            if (optionalVehicle.isEmpty()) {
                result.setStatus("error");
                result.setMessage("Vehicle not found");
                result.setData(null);
                return result;
            }

            if (parkingLot == null) {
                result.setStatus("error");
                result.setMessage("Parking lot not found");
                result.setData(null);
                return result;
            }

            RegisterVehicle vehicle = optionalVehicle.get();

            if (vehicle.getParkingLotId() == null || !vehicle.getParkingLotId().equals(request.lotId())) {
                result.setStatus("error");
                result.setMessage("Vehicle is not parked in this parking lot");
                result.setData(null);
                return result;
            }

            vehicle.setParkingLotId(null);
            vehicleRepository.save(vehicle);

            // Decrement the occupied count for the parking lot
            parkingLot.setOccupied(parkingLot.getOccupied() - 1);
            parkingLotRepository.save(parkingLot);

            result.setStatus("success");
            result.setMessage("Checkout successful");
            result.setData(vehicle);

        } catch (IllegalStateException e) {
            result.setStatus("error");
            result.setMessage(e.getMessage());
            result.setData(null);
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
            result.setMessage("An error occurred");
            result.setData(null);
        }

        return result;
    }
    
    public Result getAvailableParkingLots() {
        Result result = new Result();

        try {
            List<RegisterParkingLot> availableLots = parkingLotRepository.findAll().stream()
                    .filter(lot -> lot.getOccupied() < lot.getCapacity())
                    .collect(Collectors.toList());

            if (availableLots.isEmpty()) {
                result.setStatus("success");
                result.setMessage("No available parking lots");
                result.setData(Collections.emptyList());
            } else {
                result.setStatus("success");
                result.setMessage("Available parking lots retrieved successfully");
                result.setData(availableLots);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("error");
            result.setMessage("An error occurred while retrieving parking lots");
            result.setData(null);
        }

        return result;
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
