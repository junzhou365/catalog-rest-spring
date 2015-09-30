package catalog.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import catalog.domain.Item;
import catalog.domain.ItemManager;

@RestController
@RequestMapping(value="/api")
public class ItemController {
	private ItemManager imng = new ItemManager();
	final Logger log = Logger.getLogger(ItemController.class.getName());
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Long cId, @PathVariable Long iId) {
		Item item = imng.getItem(iId);
		return item;
	}
	
	@RequestMapping(value="/categories/{cId}/items", method=RequestMethod.GET)
	public List<Item> getItems(@PathVariable Long cId) {
		List<Item> items = imng.getItems(cId);
		return items;
	}
	
	@RequestMapping(value="/categories/{cId}/items", method=RequestMethod.POST)
	public Item createItem(@RequestBody Item item, @PathVariable Long cId) {
		Item createdItem = imng.updateItem(item, cId, false);
		return createdItem;
	}
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.PUT)
	public Item updateItem(@RequestBody Item item, @PathVariable Long cId) {
		Item updatedItem = imng.updateItem(item, cId, true);
		return updatedItem;
	}
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long iId) {
		imng.deleteItem(iId);
	}
}
