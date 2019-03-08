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

import in.desipizzas.model.FoodVendor;
import in.desipizzas.service.FoodVendorsService;

@RestController
@RequestMapping("/food-vendors")
public class FoodVendorsRestController {

	@Autowired
	FoodVendorsService foodVendorService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FoodVendor>> getAllVendorsList() {
		List<FoodVendor> allVendors = foodVendorService.getAllInfo();
		if (allVendors != null && allVendors.size() > 0) {
			return new ResponseEntity<List<FoodVendor>>(allVendors, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<FoodVendor>>(allVendors, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FoodVendor> getVendorById(@PathVariable("id") long id) {
		FoodVendor vendor = foodVendorService.getInfoById(id);
		if (vendor != null) {
			return new ResponseEntity<FoodVendor>(vendor, HttpStatus.OK);
		} else {
			return new ResponseEntity<FoodVendor>(vendor, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FoodVendor> saveVendor(@RequestBody FoodVendor foodVendor) {
		FoodVendor vendor = foodVendorService.saveInfo(foodVendor);
		return new ResponseEntity<FoodVendor>(vendor, HttpStatus.OK);
	}

}
