package in.desipizzas.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class Modifications {
	private String addedBy;
	private Date addedDate;
	private String modifiedBy;
	private Date modifiedDate;
	
}
