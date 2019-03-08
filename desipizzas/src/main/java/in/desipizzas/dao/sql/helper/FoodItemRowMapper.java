package in.desipizzas.dao.sql.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import in.desipizzas.model.FoodCategory;
import in.desipizzas.model.FoodItem;
import in.desipizzas.model.Modifications;

public class FoodItemRowMapper implements RowMapper<FoodItem> {
	@Override
	public FoodItem mapRow(ResultSet rs, int index) throws SQLException, DataAccessException {
		Modifications modifications = new Modifications(rs.getString("added_by"), rs.getDate("added_date"),
				rs.getString("modified_by"), rs.getDate("modified_date"));

		return FoodItem.builder().id(rs.getLong("id")).name(rs.getString("name"))
				.description(rs.getString("description"))
				.foodCategory(FoodCategory.builder().id(rs.getLong("food_category")).name(rs.getString("food_cat_name")).build())
				.outletPrice(rs.getFloat("outlet_price")).onlinePrice(rs.getFloat("online_price"))
				.active(rs.getBoolean("is_active")).modifications(modifications).build();

	}
}
