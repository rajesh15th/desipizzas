package in.desipizzas.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.desipizzas.model.FoodCategory;
import in.desipizzas.service.FoodCategoriesService;

@RestController
@RequestMapping("/food-categories")
public class FoodCategoriesRestController {

	@Autowired
	FoodCategoriesService FoodCategoriesService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FoodCategory>> getAllList() {
		List<FoodCategory> allVendors = FoodCategoriesService.getAllData();
		if (allVendors != null && allVendors.size() > 0) {
			return new ResponseEntity<List<FoodCategory>>(allVendors, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<FoodCategory>>(allVendors, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FoodCategory> getDataById(@PathVariable("id") long id) {
		FoodCategory vendor = FoodCategoriesService.getDataById(id);
		if (vendor != null) {
			return new ResponseEntity<FoodCategory>(vendor, HttpStatus.OK);
		} else {
			return new ResponseEntity<FoodCategory>(vendor, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FoodCategory> saveData(@RequestBody FoodCategory FoodCategory) {
		FoodCategory vendor = FoodCategoriesService.saveData(FoodCategory);
		return new ResponseEntity<FoodCategory>(vendor, HttpStatus.OK);
	}

}
