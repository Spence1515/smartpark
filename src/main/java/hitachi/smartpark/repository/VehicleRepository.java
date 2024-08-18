package hitachi.smartpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hitachi.smartpark.entity.RegisterVehicle;

public interface VehicleRepository extends JpaRepository<RegisterVehicle, String>{
	List<RegisterVehicle> findByParkingLotId(String parkingLotId);
}
