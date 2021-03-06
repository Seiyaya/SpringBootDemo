package com.seiyaya.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(value = "com.seiyaya.mapper",sqlSessionFactoryRef = SlaveDataSourceConfig.FACTORY_NAME)
public class SlaveDataSourceConfig {
	
public static final String SOUCE_NAME = "slaveDataSource";
	
	public static final String FACTORY_NAME = "slaveSessionFactory";
	
	public static final String TEMPLATE_NAME = "slaveSessionTemplate";
	
	@Bean(SOUCE_NAME)
	@Qualifier("slaveDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource slaveDataSource(SlaveConfig config) throws SQLException {
//		return DataSourceBuilder.create().build();
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(config.getUrl());
		mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXADataSource.setPassword(config.getPassword());
		mysqlXADataSource.setUser(config.getUsername());
		
		AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
		bean.setXaDataSource(mysqlXADataSource);
		bean.setUniqueResourceName("slaveDataSource");
		
		bean.setMinPoolSize(config.getMinPoolSize());
		bean.setMaxPoolSize(config.getMaxPoolSize());
		bean.setMaxLifetime(config.getMaxLifeTime());
		bean.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
		bean.setLoginTimeout(config.getLoginTimeout());
		bean.setMaintenanceInterval(config.getMaintenanceInterval());
		bean.setMaxIdleTime(config.getMaxIdleTime());
		bean.setTestQuery(config.getTestQuery());
		return mysqlXADataSource;
	}
	
	@Bean(FACTORY_NAME)
	public SqlSessionFactory slaveSessionFactory(@Qualifier(SOUCE_NAME) DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	
	@Bean("slaveTransactionManager")
	public DataSourceTransactionManager slaveDataSourceTransactionManager(@Qualifier(SOUCE_NAME) DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(TEMPLATE_NAME)
	public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier(FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
