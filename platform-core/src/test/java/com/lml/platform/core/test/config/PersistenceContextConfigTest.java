
package com.lml.platform.core.test.config;

import com.lml.platform.core.config.PersistenceContextConfig;
import com.lml.platform.core.model.authentication.User;
import com.lml.platform.core.repository.authentication.UserRepository;
import com.lml.platform.core.util.authentication.UserTokenAware;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContextConfig.class})
public class PersistenceContextConfigTest {

    /**
     * slf4j logger
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        logger.debug("setUp");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        logger.debug("tearDown");
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        logger.debug("dataSource is : {} ", dataSource);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testUserRepository() {

        User admin = userRepository.findOne(1L);
        UserTokenAware.setDefaultTestUser(admin);//模拟登陆

        User user = new User();

        user.setNickname("小乐");
        user.setPrincipal("xiaole");
        user.setCredentials("123456");

        userRepository.save(user);

        List<User> users = userRepository.findAll();
        for (User u : users) {
            logger.debug("user in : {} ", u);
        }

    }

}
