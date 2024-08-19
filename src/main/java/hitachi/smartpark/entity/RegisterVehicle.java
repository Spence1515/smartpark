package hitachi.smartpark.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@AllArgsConstructor 
@Data
public class RegisterVehicle {
		 @Id
		 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicles")
		 @SequenceGenerator(sequenceName = "vehicles_seq", allocationSize = 1, name = "vehicles")
		 private Long id;
		 
		 @Column(nullable = false, unique = true)
		 private String licensePlate;
		 private String type;
		 private String ownersName;
		 
		 @Column(nullable = true)
		 private String parkingLotId;
		 
		 public RegisterVehicle(String licensePlate, String ownersName, String type, String parkingLotId) {
		        this.licensePlate = licensePlate;
		        this.ownersName = ownersName;
		        this.type = type;
		        this.parkingLotId = parkingLotId;
		    }

		 public RegisterVehicle() {
			 
		 }
}
