package in.desipizzas.dao.sql.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import in.desipizzas.model.FoodVendor;

public class FoodItemVendorMappingRowMapper implements RowMapper<FoodVendor> {
	@Override
	public FoodVendor mapRow(ResultSet rs, int index) throws SQLException, DataAccessException {
		return FoodVendor.builder().id(rs.getLong("food_vendor_id")).itemPrice(rs.getFloat("online_price")).build();
	}
}
