package com.markany.influxbootsample;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBTemplate;

@SpringBootConfiguration
public class Application {
//	
//	private InfluxDB influxDB ;
//
//	
//	@Autowired
//	private InfluxDBTemplate<Point> influxDBTemplate;
	
//	@Autowired
//	 InfluxDBConnectionFactory connectionFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
