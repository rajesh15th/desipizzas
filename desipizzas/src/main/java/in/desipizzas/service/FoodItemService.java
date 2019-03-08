package in.desipizzas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.desipizzas.dao.FoodItemDao;
import in.desipizzas.model.FoodItem;

@Service
public class FoodItemService {
	@Autowired
	FoodItemDao foodItemDao;

	public List<FoodItem> getAllInfo() {
		return foodItemDao.fetchAllData();
	}

	public FoodItem getInfoById(long id) {
		return foodItemDao.fetchDataById(id);
	}

	public FoodItem saveInfo(FoodItem vendor) {
		if (vendor.getId() != 0) {
			foodItemDao.updateData(vendor);
			foodItemDao.deleteItemVendorMapping(vendor.getId());
			foodItemDao.insertItemvendorMapping(vendor.getId(), vendor.getModifications().getModifiedBy(), vendor.getFoodVendorsPrices());
			return vendor;
		} else {
			FoodItem item = foodItemDao.insertData(vendor);
			foodItemDao.insertItemvendorMapping(item.getId(), vendor.getModifications().getModifiedBy(), vendor.getFoodVendorsPrices());
			return item;
		}
	}

}
