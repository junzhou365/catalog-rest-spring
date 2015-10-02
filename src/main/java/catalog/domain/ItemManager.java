package catalog.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import catalog.domain.HibernateUtil;
import catalog.domain.Item;

public class ItemManager {
	private final static Logger log = Logger.getLogger(ItemManager.class.getName());
	private ImageManager immng = new ImageManager();
	
	public Item updateItem(Item item, Long cId, boolean update) {
		// Input like
		// {image: Object, title: "asdf", text: "asdf"}$promise: undefined$resolved: truedatetime: nullid: nullimage: Objecttext: "asdf"title: "asdf"__proto__: Resource
		
		if (item.getImage().getPath() == null)
			item.setImage(null);
		else
			item.setImage(immng.updateImage(item.getImage(), update));

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // no category in json, so we need to maintain relation manually
        // We have to get image from database again to maintain chain
        Category category = (Category) session.load(Category.class, cId);
        item.setCategory(category);
        if (item.getImage() != null)
        	item.getImage().getId();
        Item changedItem = item;
        
        if (update) 
        	session.update(changedItem);
        else {
        	changedItem = new Item(item);
        	session.save(changedItem);
        }
        session.getTransaction().commit();
        return changedItem;
	}
	
    public Item getItem(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Item item = (Item) session.get(Item.class, id);
        Hibernate.initialize(item.getImage());
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
        for (Item item : result) {
        	Hibernate.initialize(item.getImage());
        }
        session.getTransaction().commit();
        return result;
    }
}
