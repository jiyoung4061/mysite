package com.markany.mysite.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import com.markany.mysite.repository.GuestbookRepository;
import com.markany.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	private static final Log LOGGER = LogFactory.getLog(GuestbookService.class);
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;

	public List<GuestbookVo> getMessageList() {
//		return guestbookRepository.findAll();
		Query query = QueryBuilder.newQuery("SELECT * FROM mysite_guestbook LIMIT 1000")
		        .forDatabase("test")
		        .create();

		QueryResult queryResult = influxDBTemplate.query(query);

		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper(); // thread-safe - can be reused

		return resultMapper.toPOJO(queryResult, GuestbookVo.class);
	}

//	public void writeMessage(GuestbookVo vo) {
//		LOGGER.info("---->before:" + vo);
//		guestbookRepository.insert(vo);
//		LOGGER.info("---->after:" + vo);
//	}
//
//	public void deleteMessage(GuestbookVo vo) {
//		guestbookRepository.delete(vo);
//	}
}
