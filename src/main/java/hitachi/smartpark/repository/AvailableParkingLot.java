package hitachi.smartpark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hitachi.smartpark.entity.RegisterParkingLot;

public interface AvailableParkingLot extends JpaRepository<RegisterParkingLot, Long> {
    List<RegisterParkingLot> findByOccupiedLessThan(Integer capacity);
}
