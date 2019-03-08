package in.desipizzas.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class FoodItem {
	private long id;
	private String name;
	private String description;
	private FoodCategory foodCategory;
	private float outletPrice;
	private float onlinePrice;
	private boolean active;
	private List<FoodVendor> foodVendorsPrices;
	private Modifications modifications;
}