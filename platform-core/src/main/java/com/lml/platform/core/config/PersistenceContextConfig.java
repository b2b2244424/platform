
package com.lml.platform.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.lml.platform.core.model.authentication.User;
import com.lml.platform.core.util.authentication.UserTokenAware;

@Configuration
@PropertySource("classpath:/config/database-config.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.lml.platform.core.repository" })
@EnableJpaAuditing
public class PersistenceContextConfig {

	/***
	 * slf4j logger
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 环境
	 */
	@Autowired
	private Environment env;

	/***
	 * 模型映射包扫描
	 */
	private final String MODEL_MAPPING_PACKAGES_TO_SCAN = "com.lml.platform.core.model";

	/***
	 * 数据源bean,使用阿里巴巴的数据源
	 * 
	 * @return 数据源
	 */
	@Bean(name = "druidDataSource", initMethod = "init", destroyMethod = "close")
	public DataSource druidDataSource() {
		String connectionUrl = env.getRequiredProperty("db.connection.url");// 连接的url
		String connectionUsername = env.getRequiredProperty("db.connection.username"); // 连接的用户名
		String connectionPassword = env.getRequiredProperty("db.connection.password");// 连接的密码

		logger.debug("connection url is :{} ", connectionUrl);
		logger.debug("connection username is :{} ", connectionUsername);
		logger.debug("connection password is :{} ", connectionPassword);

		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(connectionUrl);
		ds.setUsername(connectionUsername);
		ds.setPassword(connectionPassword);

		// 一些详细的配置后面待写
		return ds;
	}

	/***
	 * 事务移除转换
	 * 
	 * @return 事务移除转换
	 */
	@Bean(name = "exceptionTranslation")
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		PersistenceExceptionTranslationPostProcessor petpp = new PersistenceExceptionTranslationPostProcessor();
		return petpp;
	}

	/***
	 * jpa hibernate 属性配置
	 * 
	 * @return jap属性配置
	 */
	@Bean(name = "jpaProperties")
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		String isShowSQL = env.getProperty("hibernate.show_sql", "true");
		String isFormatSQL = env.getProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.show_sql", isShowSQL);
		properties.setProperty("hibernate.format_sql", isFormatSQL);
		return properties;
	}

	/***
	 * 适配器
	 */
	@Bean(name = "jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setShowSql(true);
		jva.setGenerateDdl(true);
		String db = env.getRequiredProperty("db.connection.database");// 这里后面优化配置

		logger.debug("connection database is :{} ", db);

		Database dbType = Database.valueOf(db);
		jva.setDatabase(dbType);
		return jva;
	}

	/***
	 * 本地连接实体管理工厂bean
	 * 
	 * @param dataSource
	 *            数据源
	 * @param jpaVendorAdapter
	 *            适配器
	 * @return 本地连接实体管理工厂bean
	 */
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("druidDataSource") DataSource dataSource, @Qualifier("jpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter, @Qualifier("jpaProperties") Properties jpaProperties) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setJpaVendorAdapter(jpaVendorAdapter);

		logger.debug("model mapping packages to scan is :{} ", MODEL_MAPPING_PACKAGES_TO_SCAN);

		em.setPackagesToScan(MODEL_MAPPING_PACKAGES_TO_SCAN);

		em.setJpaProperties(jpaProperties);
		return em;
	}

	/***
	 * 事务管理
	 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

	@Bean(name = "auditorAware")
	public AuditorAware<User> auditorAware() {
		// UserTokenAware userTokenAware = new UserTokenAware();
		UserTokenAware userTokenAware = new UserTokenAware(1L);
		return userTokenAware;
	}

	@Bean(name = "dateTimeProvider")
	public DateTimeProvider dateTimeProvider() {
		return CurrentDateTimeProvider.INSTANCE;
	}

}
