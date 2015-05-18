package springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springbook.user.dao.UserDao;
import springbook.user.dao.UserDaoJdbc;
import springbook.user.service.DummyMailSender;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceImpl;
import springbook.user.sqlservice.EmbeddedDbSqlRegistry;
import springbook.user.sqlservice.OxmSqlService;
import springbook.user.sqlservice.SqlRegistry;
import springbook.user.sqlservice.SqlService;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ImportResource("/test-applicationContext.xml")
public class TestApplicationContext {

    /**
     * DB연결과 트랜잭션
     */

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource datasource = new SimpleDriverDataSource();

        datasource.setDriverClass(com.mysql.jdbc.Driver.class);
        datasource.setUrl("jdbc:mysql://localhost/test");
        datasource.setUsername("root");
        datasource.setPassword("1234");

        return datasource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }

    /**
     * 어플리케이션 로직 & 테스트
     */

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc userDao = new UserDaoJdbc();
        userDao.setJdbcTemplate(jdbcTemplate());
        userDao.setSqlService(sqlService());
        return userDao;
    }

    @Bean
    public UserService userService() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao());
        userService.setMailSender(mailSender());
        return userService;
    }

    /*@Bean
    public UserService testUserService() {
        TestUserService userService = new TestUserService();
        userService.setUserDao(userDao());
        userService.setMailSender(mailSender());
        return userService;
    }*/

    /**
     * SQL 서비스
     */

    @Bean
    public SqlService sqlService() {
        OxmSqlService sqlService = new OxmSqlService();
        sqlService.setUnmarshaller(unmarshaller());
        sqlService.setSqlRegistry(sqlRegistry());
        return sqlService;
    }

    @Bean
    public DataSource embeddedDatabase() {
        return new EmbeddedDatabaseBuilder().setName("embeddedDatabase")
                                            .setType(EmbeddedDatabaseType.HSQL)
                                            .addScript("classpath:/schema.sql")
                                            .build();
    }

    @Bean
    public SqlRegistry sqlRegistry() {
        EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
        sqlRegistry.setDataSource(embeddedDatabase());
        return sqlRegistry;
    }

    @Bean
    public Unmarshaller unmarshaller() {
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setContextPath("springbook.user.sqlservice.jaxb");
        return unmarshaller;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
