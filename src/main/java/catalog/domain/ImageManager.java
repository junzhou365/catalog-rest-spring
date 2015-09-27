package catalog.domain;

import java.util.List;

import org.hibernate.Session;

import catalog.domain.HibernateUtil;

public class ImageManager {
    public void addImage(Image image) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(image);
        session.getTransaction().commit();
    }

    public Image findImage(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Image image = (Image) session.get(Image.class, id);
        session.getTransaction().commit();
        return image;
    }
    
    public void deleteImage(Long id) {
    	//remove all items in the category
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	Image image = (Image) session.get(Image.class, id);
    	if (image != null) {
    		session.delete(image);
    		session.flush();
    		session.getTransaction().commit();
    		
    	}
    }

    @SuppressWarnings("rawtypes")
	private List listImages() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Image").list();
        session.getTransaction().commit();
        return result;
    }
    
    @SuppressWarnings("rawtypes")
	public void listAll() {
    	List images = listImages();
    	for (int i = 0; i < images.size(); i++) {
    		Image image = (Image) images.get(i);
    		System.out.println("Category: id:" + 
    				image.getId() + " " + image.getTitle() + " " + image.getPath());
    	}
    }
}
