package in.desipizzas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class FoodCategory {
	private long id;
	private String name;
	private boolean active;
	private Modifications modifications;
}