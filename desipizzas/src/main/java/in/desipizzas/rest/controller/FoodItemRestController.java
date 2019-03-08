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

import in.desipizzas.model.FoodItem;
import in.desipizzas.service.FoodItemService;

@RestController
@RequestMapping("/food-item")
public class FoodItemRestController {

	@Autowired
	FoodItemService foodItemService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FoodItem>> getAllList() {
		List<FoodItem> allInfo = foodItemService.getAllInfo();
		if (allInfo != null && allInfo.size() > 0) {
			return new ResponseEntity<List<FoodItem>>(allInfo, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<FoodItem>>(allInfo, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FoodItem> getDataById(@PathVariable("id") long id) {
		FoodItem info = foodItemService.getInfoById(id);
		if (info != null) {
			return new ResponseEntity<FoodItem>(info, HttpStatus.OK);
		} else {
			return new ResponseEntity<FoodItem>(info, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FoodItem> saveData(@RequestBody FoodItem data) {
		FoodItem info = foodItemService.saveInfo(data);
		return new ResponseEntity<FoodItem>(info, HttpStatus.OK);
	}

}
