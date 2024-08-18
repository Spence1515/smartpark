package hitachi.smartpark.model;

import lombok.Data;

@Data
public class Result {
	private String status;
	private String message;
	private Object data;
}
