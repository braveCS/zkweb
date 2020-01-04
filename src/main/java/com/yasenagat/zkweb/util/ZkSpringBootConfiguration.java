package com.yasenagat.zkweb.util;


import java.beans.PropertyVetoException;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ZkSpringBootConfiguration {
	@Autowired
    private Environment env;
	
    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    public DataSource getDataSource(){
    	ComboPooledDataSource dataSource= org.springframework.boot.jdbc.DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    	dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUser(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        try {
			dataSource.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			return null;
		}
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("spring.datasource.initial-pool-size")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("spring.datasource.min-pool-size")));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("spring.datasource.max-pool-size")));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("spring.datasource.acquire-increment")));
        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("spring.datasource.idle-connection-test-period")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("spring.datasource.max-idle-time")));
        dataSource.setMaxStatements(Integer.parseInt(env.getProperty("spring.datasource.max-statements")));
        dataSource.setAcquireRetryAttempts(Integer.parseInt(env.getProperty("spring.datasource.acquire-retry-attempts")));
        dataSource.setBreakAfterAcquireFailure(Boolean.parseBoolean(env.getProperty("spring.datasource.break-after-acquire-failure")));
        return dataSource;
    }
    
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }
    
  @Bean
  public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
  	RequestMappingHandlerAdapter requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
  	return requestMappingHandlerAdapter;
  }
  
}

