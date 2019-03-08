package in.desipizzas.dao.sql.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import in.desipizzas.model.FoodCategory;
import in.desipizzas.model.FoodItem;
import in.desipizzas.model.Modifications;

public class FoodItemExtractor implements ResultSetExtractor<FoodItem> {
	@Override
	public FoodItem extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {

			Modifications modifications = new Modifications(rs.getString("added_by"), rs.getDate("added_date"),
					rs.getString("modified_by"), rs.getDate("modified_date"));

			return FoodItem.builder().id(rs.getLong("id")).name(rs.getString("name"))
					.description(rs.getString("description"))
					.foodCategory(FoodCategory.builder().id(rs.getLong("food_category"))
							.build())
					.outletPrice(rs.getFloat("outlet_price")).onlinePrice(rs.getFloat("online_price"))
					.active(rs.getBoolean("is_active")).modifications(modifications).build();

		}
		return null;
	}
}
