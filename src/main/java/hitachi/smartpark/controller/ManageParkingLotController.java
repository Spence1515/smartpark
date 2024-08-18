package hitachi.smartpark.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hitachi.smartpark.model.ParkingLotRequest;
import hitachi.smartpark.model.RegisterParkingLotRequest;
import hitachi.smartpark.model.RegisterVehicleRequest;
import hitachi.smartpark.model.Result;
import hitachi.smartpark.service.ParkingLotService;
import hitachi.smartpark.service.RegisterParkingLotService;
import hitachi.smartpark.service.RegisterVehicleService;
import hitachi.smartpark.service.VehicleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ManageParkingLotController {
	private final RegisterParkingLotService registerParkingLotService;
	private final RegisterVehicleService registerVehicleService;
	private final ParkingLotService parkingLotService;
	private final VehicleService vehicleService;
	
	@PostMapping(value="/registerParkingLot")
	public ResponseEntity<Result> registerParkingLot(@RequestBody RegisterParkingLotRequest request) {
		Result result = this.registerParkingLotService.registerParkingLot(request);
		return  new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value="/registerVehicle")
	public ResponseEntity<Result> registerVehicle(@RequestBody RegisterVehicleRequest request) {
		Result result = this.registerVehicleService.registerVehicle(request);
		return  new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping(value="/checkin")
	public Result checkInVehicle(@RequestBody ParkingLotRequest request) {
	     return this.parkingLotService.checkInVehicle(request);
	}
	
	@PatchMapping(value="/checkout")
	public Result checkOutVehicle(@RequestBody ParkingLotRequest request) {
	     return this.parkingLotService.checkOutVehicle(request);
	}
	
    @GetMapping(value="/available")
    public Result getAvailableParkingLots() {
        return parkingLotService.getAvailableParkingLots();
    }
    
    @GetMapping("/in-lot/{parkingLotId}")
    public Result getVehiclesInParkingLot(@PathVariable String parkingLotId) {
        return vehicleService.getVehiclesInParkingLot(parkingLotId);
    }

}
