package junzhou365.controller.api;

import junzhou365.App;
import junzhou365.config.SecurityConfig;
import junzhou365.domain.dao.CategoryDaoImpl;
import junzhou365.domain.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Julian on 10/27/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {App.class, SecurityConfig.class})
@WebAppConfiguration
public class CatalogControllerTest {
    private MockMvc mockMvc;

    private Category category;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void setup() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        this.category = new Category("who");
        session.save(this.category);
        session.getTransaction().commit();

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
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
    public void testHomepage() throws Exception {
        mockMvc.perform(get("/catalog")).andExpect(status().isFound()).andExpect(redirectedUrl("/catalog/"));
    }

    @Test
    public void tesGetCategory() throws Exception {
        mockMvc.perform(get("/catalog/api/categories/1")).andExpect(status().isOk());
    }

    @Test
    public void testCreateCategory() throws Exception {
        MvcResult mvcResult = mockMvc
        .perform(post("/catalog/api/categories").content(this.json(new Category("Why"))).contentType(contentType)).andReturn();
//                .andExpect(status().is4xxClientError());.with(httpBasic("111", "password"))
        MockHttpServletResponse response = mvcResult.getResponse();
    }

    protected String json(Object o) throws IOException {
        System.out.println(o);
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
