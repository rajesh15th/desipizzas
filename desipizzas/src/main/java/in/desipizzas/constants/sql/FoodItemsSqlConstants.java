package in.desipizzas.constants.sql;

public interface FoodItemsSqlConstants {
	String TABLE_NAME = "dp_food_items";
	String FETCH_ALL = "select ci.*,cg.name as food_cat_name from " + TABLE_NAME 
			+ " ci inner join dp_food_items_category cg on ci.food_category=cg.id "
			+ " order by 1";
	String FETCH_BY_ID = "select * from " + TABLE_NAME + " where id = ?";
	String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "(\n"
			+ "	id, name, description, food_category, outlet_price, online_price, is_active, added_by )\n"
			+ "	VALUES (nextval('food_item_seq'), ?, ?, ?, ?, ?, ?, ?);";
	String UPDATE_DATA = "UPDATE " + TABLE_NAME + "\n"
			+ "	SET name=?, description=?, food_category=?, outlet_price=?, online_price=?, is_active= ?, modified_by=?, modified_date=now() \n"
			+ "	WHERE id=?;";
	
	String ITEM_VENDOR_MAPPING_BY_ID = "	select * from dp_food_item_vendor_mapping where food_item_id = ? order by food_vendor_id asc ";
	
	String INSERT_ITEM_VENDOR_MAPPING = "INSERT INTO dp_food_item_vendor_mapping(\n" + 
			"	food_item_id, food_vendor_id, online_price, added_by)\n" + 
			"	VALUES (?, ?, ?, ?);";
	

	String DELETE_ITEM_VENDOR_MAPPING_BY_ITEM_ID = "delete from dp_food_item_vendor_mapping where food_item_id = ? ";
	
	
}