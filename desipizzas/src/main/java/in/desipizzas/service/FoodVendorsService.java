package in.desipizzas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.desipizzas.dao.FoodVendorDao;
import in.desipizzas.model.FoodVendor;

@Service
public class FoodVendorsService {
	@Autowired 
	FoodVendorDao foodVendorDao;
	
	public List<FoodVendor> getAllInfo() {
		return foodVendorDao.fetchAllData();
	}
	
	public FoodVendor getInfoById(long id) {
		return foodVendorDao.fetchDataById(id);
	}
	
	public FoodVendor saveInfo (FoodVendor vendor) {
		if (vendor.getId() != 0 ) {
			foodVendorDao.updateData(vendor);
			return vendor;
		} else {
			return foodVendorDao.insertData(vendor);
		}
	}
	
}
