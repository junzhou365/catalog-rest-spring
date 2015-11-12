package junzhou365.domain.dao;

import junzhou365.config.HibernateUtilConfig;
import junzhou365.domain.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Julian on 11/11/2015.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoTest {
    private SessionFactory sessionFactory = HibernateUtilConfig.getSessionFactory();
    private CategoryDao categoryDao;
    private Category category;
    private Long category_id;

    @Before
    public void setup() throws Exception {
        this.categoryDao = new CategoryDaoImpl(sessionFactory);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        this.category = new Category("who");
        session.save(this.category);
        session.getTransaction().commit();
    }

    @After
    public void tearDown() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(this.category);
        session.flush();
        session.getTransaction().commit();
    }

    @Test
    public void testGetCategory() {
        assertNotNull(categoryDao.getCategory(category.getId()));
    }
}
