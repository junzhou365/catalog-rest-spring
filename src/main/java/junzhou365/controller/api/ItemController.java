package junzhou365.controller.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import junzhou365.domain.dao.ItemDao;
import junzhou365.domain.model.Item;

@RestController
@RequestMapping(value= "/catalog/api")
public class ItemController {
	
	final Logger log = Logger.getLogger(ItemController.class.getName());
	
	private ItemDao itemDao;
	
	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Long cId, @PathVariable Long iId) {
		Item item = itemDao.getItem(iId);
		return item;
	}
	
	@RequestMapping(value="/categories/{cId}/items", method=RequestMethod.GET)
	public List<Item> getItems(@PathVariable Long cId) {
		List<Item> items = itemDao.getItems(cId);
		return items;
	}
	
	@RequestMapping(value="/categories/{cId}/items", method=RequestMethod.POST)
	public Item createItem(@RequestBody Item item, @PathVariable Long cId) {
		Item createdItem = itemDao.createItem(item, cId);
		return createdItem;
	}
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.PUT)
	public Item updateItem(@RequestBody Item item, @PathVariable Long cId) {
		Item updatedItem = itemDao.updateItem(item, cId);
		return updatedItem;
	}
	
	@RequestMapping(value="/categories/{cId}/items/{iId}", method=RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long iId) {
		itemDao.deleteItem(iId);
	}
	
	@RequestMapping(value="/getLatestItems", method=RequestMethod.GET)
	public List<Item> getLatestItems() {
		return itemDao.getLatestItems();
	}
}
