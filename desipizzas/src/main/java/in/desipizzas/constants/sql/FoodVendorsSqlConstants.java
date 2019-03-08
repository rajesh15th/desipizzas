package in.desipizzas.constants.sql;

public interface FoodVendorsSqlConstants {
	String TABLE_NAME = "dp_food_vendors";
	String FETCH_ALL = "select * from " + TABLE_NAME + " order by 1";
	String FETCH_BY_ID = "select * from " + TABLE_NAME + " where id = ?";
	String INSERT_DATA = "INSERT INTO " + TABLE_NAME + "(\n"
			+ "	id, vendor_name, vendor_key, commission_percentage, gst_commission, added_by,is_active,pg_charges )\n"
			+ "	VALUES (nextval('food_vendors_sql'), ?, ?, ?, ?, ?, ?, ?);";
	String UPDATE_DATA = "UPDATE " + TABLE_NAME + "\n"
			+ "	SET vendor_name=?, vendor_key=?, commission_percentage=?, gst_commission=?, modified_by=?, modified_date=now(), is_active= ?, pg_charges = ? \n"
			+ "	WHERE id=?;";
}