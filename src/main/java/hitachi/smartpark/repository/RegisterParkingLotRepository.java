package hitachi.smartpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hitachi.smartpark.entity.RegisterParkingLot;

public interface RegisterParkingLotRepository extends JpaRepository<RegisterParkingLot, Long>{
	boolean existsByLotId(String lotId);
	RegisterParkingLot findByLotId(String lotId);
}
