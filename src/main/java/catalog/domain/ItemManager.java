package catalog.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import catalog.domain.HibernateUtil;
import catalog.domain.Item;

public class ItemManager {	
	public Item updateItem(Item item, Long cId, boolean update) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.load(Category.class, cId);
        item.setDatetime(new Date());
        item.setCategory(category);
        if (update)
        	session.update(item);
        else
        	session.save(item);
        session.getTransaction().commit();
        return item;
	}
	
    public Item getItem(Long id) {
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
    	}
    	session.getTransaction().commit();
    }
    
	public List<Item> getItems(Long cId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Item> result = session.createCriteria(Item.class).add(Restrictions.eq("category.id", cId)).list();
        session.getTransaction().commit();
        return result;
    }
}
