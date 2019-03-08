package in.desipizzas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class FoodVendor {
	private long id;
	private String vendorName;
	private String vendorKey;
	private float commissionRate;
	private float gstOnCommision;
	private float pgCharges;
	private boolean active;
	private Modifications modifications;
	private float itemPrice;
}