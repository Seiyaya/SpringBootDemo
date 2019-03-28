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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(value = "com.seiyaya.dao",sqlSessionFactoryRef = DataSourceConfig.FACTORY_NAME)
public class DataSourceConfig {
	public static final String SOUCE_NAME = "primaryDataSource";
	
	public static final String FACTORY_NAME = "primarySessionFactory";
	
	public static final String TEMPLATE_NAME = "primarySessionTemplate";
	
	@Bean(SOUCE_NAME)
	@Qualifier("primaryDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource primaryDataSource(PrimaryConfig config) throws SQLException {
//		return DataSourceBuilder.create().build();		//集中式的数据源配置
			MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
			mysqlXADataSource.setUrl(config.getUrl());
			mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
			mysqlXADataSource.setPassword(config.getPassword());
			mysqlXADataSource.setUser(config.getUsername());
			
			AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
			bean.setXaDataSource(mysqlXADataSource);
			bean.setUniqueResourceName("primaryDataSource");
			
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
	@Primary
	public SqlSessionFactory primarySessionFactory(@Qualifier(SOUCE_NAME) DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
//		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
		return bean.getObject();
	}
	
	@Bean("primaryTransactionManager")
	@Primary
	public DataSourceTransactionManager primaryDataSourceTransactionManager(@Qualifier(SOUCE_NAME) DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(TEMPLATE_NAME)
	@Primary
	public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier(FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
