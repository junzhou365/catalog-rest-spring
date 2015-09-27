package catalog.domain;

import java.util.List;

import org.hibernate.Session;

import catalog.domain.HibernateUtil;
import catalog.domain.Item;

public class ItemManager {
	public void addItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
	}
	
    public Item findItem(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Item item = (Item) session.get(Item.class, id);
        session.getTransaction().commit();
        return item;
    }
    
    public void deleteItem(Long id) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Item item = (Item)session.get(Item.class, id);
    	if (item != null) {
    		session.delete(item);
    		session.flush();
    		session.getTransaction().commit();
    		
    	}
    }

    @SuppressWarnings("rawtypes")
	private List listItems() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
	public void listAll() {
    	List items = listItems();
    	for (int i = 0; i < items.size(); i++) {
    		Item item = (Item) items.get(i);
    		System.out.println("Item id:" + 
    				item.getId() + " " + item.getTitle() + " " + item.getText() + " " + item.getDatetime());
    	}
    }
}
