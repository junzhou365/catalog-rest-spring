package junzhou365.domain.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import junzhou365.domain.model.Item;

public class ItemDaoImpl implements ItemDao {
	private final static Logger log = Logger.getLogger(ItemDaoImpl.class.getName());
	private ImageDao imageDao;
	
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Item updateItem(Item item, Long cId, boolean update) {
		// Input like
		// {image: Object, title: "asdf", text: "asdf"}$promise: undefined$resolved: truedatetime: nullid: nullimage: Objecttext: "asdf"title: "asdf"__proto__: Resource

		if (item.getImage().getPath() == null)
			item.setImage(null);
		else
			item.setImage(imageDao.updateImage(item.getImage(), update));

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        // no category in json, so we need to maintain relation manually
        // We have to get image from database again to maintain chain
        Hibernate.initialize(item.getCategory());
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
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Item item = (Item) session.get(Item.class, id);
        Hibernate.initialize(item.getImage());
        session.getTransaction().commit();
        return item;
    }

    public void deleteItem(Long id) {
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	Item item = (Item)session.get(Item.class, id);
    	if (item != null) {
    		session.delete(item);
    		session.flush();
    	}
    	session.getTransaction().commit();
    }

	public List<Item> getItems(Long cId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Item> result = session.createCriteria(Item.class).add(Restrictions.eq("category.id", cId)).list();
        for (Item item : result) {
        	Hibernate.initialize(item.getImage());
					Hibernate.initialize(item.getCategory());
        }
        session.getTransaction().commit();
        return result;
    }
	
	public List<Item> getLatestItems() {
		//Output will be limited to 12 items
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Item> result = session.createCriteria(Item.class)
							.addOrder(Order.desc("datetime"))
							.setMaxResults(12)
							.list();
        for (Item item : result) {
        	Hibernate.initialize(item.getImage());
					Hibernate.initialize(item.getCategory());
        }
        session.getTransaction().commit();
        return result;
    }
}
