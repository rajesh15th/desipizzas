package in.desipizzas.constants.sql;

public interface FoodCategoriesSqlConstants {
	String TABLE_NAME = "dp_food_items_category";

	String FETCH_ALL = "select * from " + TABLE_NAME + " order by 1";

	String FETCH_BY_ID = "select * from " + TABLE_NAME + " where id = ?";

	String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "(\n" + "	id, name, is_active, added_by )\n"
			+ "	VALUES (nextval('food_cat_names_seq'), ?, ?, ? );";

	String UPDATE_DATA = "UPDATE " + TABLE_NAME + "\n"
			+ "	SET name=?, is_active= ?, modified_by=?, modified_date=now() \n" + "	WHERE id=?;";
}