package in.desipizzas.dao.sql.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import in.desipizzas.model.FoodVendor;
import in.desipizzas.model.Modifications;

public class FoodVendorsRowMapper implements RowMapper<FoodVendor> {
	@Override
	public FoodVendor mapRow(ResultSet rs, int index) throws SQLException, DataAccessException {
		Modifications modifications = new Modifications(rs.getString("added_by"), rs.getDate("added_date"),
				rs.getString("modified_by"), rs.getDate("modified_date"));
		return FoodVendor.builder().id(rs.getLong("id")).vendorName(rs.getString("vendor_name"))
				.vendorKey(rs.getString("vendor_key")).commissionRate(rs.getFloat("commission_percentage"))
				.gstOnCommision(rs.getFloat("gst_commission")).active(rs.getBoolean("is_active"))
				.pgCharges(rs.getFloat("pg_charges")).modifications(modifications).build();
	}
}
