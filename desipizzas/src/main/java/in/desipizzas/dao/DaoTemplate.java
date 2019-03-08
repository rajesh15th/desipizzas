package in.desipizzas.dao;

import java.util.List;

public interface DaoTemplate<T> {
	List<T> fetchAllData();

	T fetchDataById(long id);

	T insertData(T data);

	void updateData(T data);

}
