package hitachi.smartpark.model;

public record RegisterParkingLotRequest(String lotId, String location, Integer capacity, Integer occupied) {
	
}
