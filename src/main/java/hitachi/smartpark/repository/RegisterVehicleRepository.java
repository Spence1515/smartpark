package hitachi.smartpark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hitachi.smartpark.entity.RegisterVehicle;

public interface RegisterVehicleRepository extends JpaRepository<RegisterVehicle, String>{
	boolean existsByLicensePlate(String licensePlate);
	Optional<RegisterVehicle> findByLicensePlate(String id);
}
