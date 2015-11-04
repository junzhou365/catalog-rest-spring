//package catalog.controller.api;
//
//import catalog.domain.dao.CategoryDao;
//import catalog.domain.model.Category;
//import catalog.user.dao.UserDao;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import catalog.App;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
///**
// * Created by Julian on 10/27/2015.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//@WebAppConfiguration
//public class CatalogControllerTest {
//    private MockMvc mockMvc;
//
//    private Category category;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private CategoryDao categoryDao;
//
//    @Before
//    public void setup() throws Exception {
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();
//        this.category = categoryDao.updateCategory(new Category("huhu"), false);
//    }
//}
