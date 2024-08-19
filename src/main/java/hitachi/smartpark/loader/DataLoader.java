package hitachi.smartpark.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import hitachi.smartpark.entity.RegisterParkingLot;
import hitachi.smartpark.entity.RegisterVehicle;
import hitachi.smartpark.repository.RegisterParkingLotRepository;
import hitachi.smartpark.repository.RegisterVehicleRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RegisterParkingLotRepository parkingLotRepository;

    @Autowired
    private RegisterVehicleRepository vehicleRepository;

    @Override
    public void run(String... args) throws Exception {
        parkingLotRepository.save(new RegisterParkingLot("LotE", 70, "Location E", 0));
        parkingLotRepository.save(new RegisterParkingLot("LotF", 20, "Location F", 0));

        vehicleRepository.save(new RegisterVehicle("EFG-123", "Charlie Brown", "Convertible", null));
        vehicleRepository.save(new RegisterVehicle("HIJ-456", "Lucy van Pelt", "SUV", null));
    }
}