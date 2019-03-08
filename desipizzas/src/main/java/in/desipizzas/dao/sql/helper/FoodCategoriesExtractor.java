package in.desipizzas.dao.sql.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import in.desipizzas.model.FoodCategory;
import in.desipizzas.model.Modifications;

public class FoodCategoriesExtractor implements ResultSetExtractor<FoodCategory> {
	@Override
	public FoodCategory extractData(ResultSet rs) throws SQLException, DataAccessException {
		if (rs.next()) {
			Modifications modifications = new Modifications(rs.getString("added_by"), rs.getDate("added_date"), rs.getString("modified_by"), rs.getDate("modified_date"));
			return new FoodCategory(rs.getLong("id"), rs.getString("name"), rs.getBoolean("is_active"), modifications );
			
		}
		return null;
	}
}
