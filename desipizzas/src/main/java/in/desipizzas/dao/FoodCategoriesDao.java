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

import in.desipizzas.constants.sql.FoodCategoriesSqlConstants;
import in.desipizzas.dao.sql.helper.FoodCategoriesExtractor;
import in.desipizzas.dao.sql.helper.FoodCategoriesRowMapper;
import in.desipizzas.model.FoodCategory;

@Repository
public class FoodCategoriesDao implements DaoTemplate<FoodCategory> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<FoodCategory> fetchAllData() {
		return jdbcTemplate.query(FoodCategoriesSqlConstants.FETCH_ALL, new FoodCategoriesRowMapper());
	}

	@Override
	public FoodCategory fetchDataById(long id) {
		return jdbcTemplate.query(FoodCategoriesSqlConstants.FETCH_BY_ID, new Object[] { id },
				new FoodCategoriesExtractor());
	}

	@Override
	public FoodCategory insertData(FoodCategory data) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(FoodCategoriesSqlConstants.INSERT_DATA,
						new String[] { "id" });
				pst.setString(1, data.getName());
				pst.setBoolean(2, data.isActive());
				pst.setString(3, data.getModifications().getAddedBy());
				return pst;
			}
		}, keyHolder);
		Map<String, Object> keys = keyHolder.getKeys();
		Long key = (Long) keys.get("id");
		data.setId(key);
		return data;
	}

	@Override
	public void updateData(FoodCategory data) {
		jdbcTemplate.update(FoodCategoriesSqlConstants.UPDATE_DATA, data.getName(), data.isActive(),
				data.getModifications().getModifiedBy(), data.getId());
	}

}
