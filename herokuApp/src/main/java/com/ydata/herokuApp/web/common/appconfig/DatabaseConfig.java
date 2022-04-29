package com.ydata.herokuApp.web.common.appconfig;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = "com.ydata.herokuApp.web.potal.mapper")
@EnableTransactionManagement
public class DatabaseConfig {

	
	
    
    final String DATASOURCE_PRE_FIX="dev";

    @Value("${"+DATASOURCE_PRE_FIX+".datasource.deviceClassName:}")
    String deviceClassName;

    @Value("${"+DATASOURCE_PRE_FIX+".datasource.url:}")
    String dbUrl;

    @Value("${"+DATASOURCE_PRE_FIX+".datasource.username:}")
    String username;

    @Value("${"+DATASOURCE_PRE_FIX+".datasource.password:}")
    String password;
    

    @Bean
    @Primary
    public DataSource dataSource() {
    	System.out.println(deviceClassName);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(deviceClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return (DataSource)dataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
    	return new DataSourceTransactionManager(dataSource);
    }
    
	@Bean
	@Primary
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//sqlSessionFactoryBean.setConfigLocation(pathResolver.getResource("classpath:/mybatis/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(pathResolver.getResources("classpath:/mapper/postgresql/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
    
	@Bean
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
}
