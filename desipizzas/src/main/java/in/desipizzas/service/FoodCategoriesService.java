package in.desipizzas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.desipizzas.dao.FoodCategoriesDao;
import in.desipizzas.model.FoodCategory;

@Service
public class FoodCategoriesService {
	@Autowired 
	FoodCategoriesDao foodCategoriesDao;
	
	public List<FoodCategory> getAllData() {
		return foodCategoriesDao.fetchAllData();
	}
	
	public FoodCategory getDataById(long id) {
		return foodCategoriesDao.fetchDataById(id);
	}
	
	public FoodCategory saveData(FoodCategory vendor) {
		if (vendor.getId() != 0 ) {
			foodCategoriesDao.updateData(vendor);
			return vendor;
		} else {
			return foodCategoriesDao.insertData(vendor);
		}
	}
	
}
