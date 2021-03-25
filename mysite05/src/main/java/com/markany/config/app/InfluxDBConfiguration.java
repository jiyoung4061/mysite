package com.markany.config.app;

import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDBConfiguration {
	
	@Bean
	  public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties)
	  {
	    return new InfluxDBConnectionFactory(properties);
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
	
//	@Autowired
//	private Environment env;
//	
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
//	
//	@Bean
//	public PlatformTransactionManager transactionManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
}
