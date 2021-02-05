package com.markany.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.markany.mysite.service.FileuploadService;
import com.markany.mysite.service.GalleryService;
import com.markany.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.fetchList();
		model.addAttribute("list", list);
		return "gallery/index";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@RequestParam(value="comments") String comment,
			@RequestParam(value="file")MultipartFile multipartFile) {		
		String url = fileuploadService.restore(multipartFile);
		
		GalleryVo vo = new GalleryVo();
		vo.setComment(comment);
		vo.setImageUrl(url);
		
		galleryService.addFile(vo);
		
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.deleteFile(no);
		return "redirect:/gallery";
	}
}
