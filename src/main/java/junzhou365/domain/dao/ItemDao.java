package junzhou365.domain.dao;

import java.util.List;

import junzhou365.domain.model.Item;

public interface ItemDao {
	public Item updateItem(Item item, Long cId);

	public Item createItem(Item item, Long cId);

    public Item getItem(Long id);

    public void deleteItem(Long id);

	public List<Item> getItems(Long cId);
	
	public List<Item> getLatestItems();
}
