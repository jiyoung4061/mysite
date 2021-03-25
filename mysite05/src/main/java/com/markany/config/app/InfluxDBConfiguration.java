package com.markany.config.app;

import org.apache.ibatis.mapping.Environment;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
@PropertySource("classpath:com/markany/mysite/config/app/properties/jdbc.properties")
public class InfluxDBConfiguration {
	
	@Autowired
	private Environment env;

//	@Bean
//	public DataSource datasource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//		dataSource.setUrl(env.getProperty("jdbc.url"));
//		dataSource.setUsername(env.getProperty("jdbc.username"));
//		dataSource.setPassword(env.getProperty("jdbc.password"));
//		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class)); // getProperty반환값이 string이라 int값으로 바꿔줌!
//		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
//		
//		return dataSource;
//	}
	
	@Bean
	  public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties)
	  {
		InfluxDBConnectionFactory influxDBConnectionFactory = new InfluxDBConnectionFactory(properties);
		return influxDBConnectionFactory;
	  }

	  @Bean
	  public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory)
	  {
	    /*
	     * You can use your own 'PointCollectionConverter' implementation, e.g. in case
	     * you want to use your own custom measurement object.
	     */
	    return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
	  }

	  @Bean
	  public DefaultInfluxDBTemplate defaultTemplate(final InfluxDBConnectionFactory connectionFactory)
	  {
	    /*
	     * If you are just dealing with Point objects from 'influxdb-java' you could
	     * also use an instance of class DefaultInfluxDBTemplate.
	     */
	    return new DefaultInfluxDBTemplate(connectionFactory);
	  }
	
	
	
//	@Bean
//	public PlatformTransactionManager transactionManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
}
