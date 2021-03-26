package com.markany.mysite.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import com.markany.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	private static final Log LOGGER = LogFactory.getLog(GuestbookService.class);

	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	
	public List<GuestbookVo> getMessageList() {
		Query query = QueryBuilder.newQuery("SELECT * FROM mysite_guestbook")
				.forDatabase("test")
				.create();
		
		QueryResult queryResult = influxDBTemplate.query(query);
		return new InfluxDBResultMapper().toPOJO(queryResult, GuestbookVo.class);
	}

	public void writeMessage(GuestbookVo vo) {
		LOGGER.info("---->before:" + vo);
		
		Point point = Point.measurement("mysite_guestbook")
				.tag("name", vo.getName())
				.tag("message", vo.getMessage())
				.tag("password", vo.getPassword())
				.addField("no", 5.0)
				.build();
		
		influxDBTemplate.write(point);
		
		System.out.println("point????????" + point);
		
		LOGGER.info("---->after:" + vo);		
//		aristRepository.insert(artistVo);
//		songVo.setArtistNo(artistNo.getNo());
//		songRepository.insert(songVo);
	}

//	public void deleteMessage(GuestbookVo vo) {
//		guestbookRepository.delete(vo);
//	}
}
