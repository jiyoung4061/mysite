package com.markany.mysite.controller.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.markany.mysite.dto.JsonResult;
import com.markany.mysite.service.GuestbookService;
import com.markany.mysite.vo.GuestbookVo;

@RestController("guestbookApiController") // restcontroller하면 responsebody 안받아도됨!
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list/{no}")
	public JsonResult list(@PathVariable("no") Long startNo) {
		List<GuestbookVo> list = guestbookService.getMessageList(startNo);
		return JsonResult.success(list);
	}
	
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) { // json형식으로 받을땐 requestbody 필수!
		guestbookService.writeMessage(vo);
		vo.setPassword("");
		return JsonResult.success(vo);
	}
	
	@DeleteMapping("/delete/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no,
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		System.out.println(no + ":" + password);
		boolean result = guestbookService.deleteMessage(no, password);
		return JsonResult.success(result ? no : -1);
	}
}
