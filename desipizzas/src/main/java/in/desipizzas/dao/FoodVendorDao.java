package in.desipizzas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import in.desipizzas.constants.sql.FoodVendorsSqlConstants;
import in.desipizzas.dao.sql.helper.FoodVendorsExtractor;
import in.desipizzas.dao.sql.helper.FoodVendorsRowMapper;
import in.desipizzas.model.FoodVendor;

@Repository
public class FoodVendorDao implements DaoTemplate<FoodVendor> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FoodVendor> fetchAllData() {
		return jdbcTemplate.query(FoodVendorsSqlConstants.FETCH_ALL, new FoodVendorsRowMapper());
	}

	@Override
	public FoodVendor fetchDataById(long id) {
		return jdbcTemplate.query(FoodVendorsSqlConstants.FETCH_BY_ID, new Object[] { id }, new FoodVendorsExtractor());
	}

	@Override
	public FoodVendor insertData(FoodVendor data) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(FoodVendorsSqlConstants.INSERT_DATA, new String[] {"id"});
				pst.setString(1, data.getVendorName());
				pst.setString(2, data.getVendorKey());
				pst.setFloat(3, data.getCommissionRate());
				pst.setFloat(4, data.getGstOnCommision());
				pst.setString(5, data.getModifications().getAddedBy());
				pst.setBoolean(6, data.isActive());
				pst.setFloat(7, data.getPgCharges());
				return pst;
			}
		}, keyHolder);
		Map<String, Object> keys = keyHolder.getKeys();
		Long key =  (Long)keys.get("id");
		data.setId(key);
		return data;
	}

	@Override
	public void updateData(FoodVendor data) {
		jdbcTemplate.update(FoodVendorsSqlConstants.UPDATE_DATA, data.getVendorName(),data.getVendorKey(),data.getCommissionRate(),data.getGstOnCommision(),data.getModifications().getModifiedBy(),data.isActive(), data.getPgCharges(), data.getId());
	}

}
