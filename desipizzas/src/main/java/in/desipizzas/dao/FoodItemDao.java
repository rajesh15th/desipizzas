package in.desipizzas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.desipizzas.constants.sql.FoodItemsSqlConstants;
import in.desipizzas.dao.sql.helper.FoodItemExtractor;
import in.desipizzas.dao.sql.helper.FoodItemRowMapper;
import in.desipizzas.dao.sql.helper.FoodItemVendorMappingRowMapper;
import in.desipizzas.model.FoodItem;
import in.desipizzas.model.FoodVendor;

@Repository
public class FoodItemDao implements DaoTemplate<FoodItem> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FoodItem> fetchAllData() {
		return jdbcTemplate.query(FoodItemsSqlConstants.FETCH_ALL, new FoodItemRowMapper());
	}

	@Override
	public FoodItem fetchDataById(long id) {

		FoodItem foodItem = jdbcTemplate.query(FoodItemsSqlConstants.FETCH_BY_ID, new Object[] { id },
				new FoodItemExtractor());
		if (foodItem != null) {
			foodItem.setFoodVendorsPrices(jdbcTemplate.query(FoodItemsSqlConstants.ITEM_VENDOR_MAPPING_BY_ID,
					new Object[] { id }, new FoodItemVendorMappingRowMapper()));
		}
		return foodItem;
	}

	@Override
	public FoodItem insertData(FoodItem data) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(FoodItemsSqlConstants.INSERT_DATA, new String[] { "id" });
				pst.setString(1, data.getName());
				pst.setString(2, data.getDescription());
				pst.setLong(3, data.getFoodCategory().getId());
				pst.setFloat(4, data.getOutletPrice());
				pst.setFloat(5, data.getOnlinePrice());
				pst.setBoolean(6, data.isActive());
				pst.setString(7, data.getModifications().getAddedBy());
				return pst;
			}
		}, keyHolder);
		Map<String, Object> keys = keyHolder.getKeys();
		Long key = (Long) keys.get("id");
		data.setId(key);

		return data;
	}

	@Override
	public void updateData(FoodItem data) {
		jdbcTemplate.update(FoodItemsSqlConstants.UPDATE_DATA, data.getName(), data.getDescription(),
				data.getFoodCategory().getId(), data.getOutletPrice(), data.getOnlinePrice(), data.isActive(),
				data.getModifications().getModifiedBy(), data.getId());
	}

	public void deleteItemVendorMapping(long id) {
		jdbcTemplate.update(FoodItemsSqlConstants.DELETE_ITEM_VENDOR_MAPPING_BY_ITEM_ID, id);
	}

	public void insertItemvendorMapping(long id, String addedBy, List<FoodVendor> foodVendorsPrices) {
		if (foodVendorsPrices != null && foodVendorsPrices.size() > 0) {
			List<Object[]> data = new ArrayList<>();
			for (FoodVendor vendor : foodVendorsPrices) {
				data.add(new Object[] { id, vendor.getId(), vendor.getItemPrice(), addedBy });
			}
			jdbcTemplate.batchUpdate(FoodItemsSqlConstants.INSERT_ITEM_VENDOR_MAPPING, data);
		}
	}

}
