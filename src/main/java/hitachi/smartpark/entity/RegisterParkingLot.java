package hitachi.smartpark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_lots")
@NoArgsConstructor
@AllArgsConstructor 
@Data
public class RegisterParkingLot {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_lots")
	 @SequenceGenerator(sequenceName = "parking_lot_seq", allocationSize = 1, name = "parking_lots")
	 private Long id;

	 @Column(nullable = false, unique = true)
	 private String lotId;
	 @Column(nullable = false)
	 private Integer capacity;
	 private String location;
	 @Column(nullable = false)
	 private Integer occupied;
}
