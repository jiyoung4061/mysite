package com.markany.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.markany.mysite.repository.GalleryRepository;
import com.markany.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryRepository galleryRepository;
	
	public boolean addFile(GalleryVo vo) {
		return galleryRepository.insert(vo) == 1;
	}

	public List<GalleryVo> fetchList() {
		return galleryRepository.findAll();
	}

	public boolean deleteFile(Long no) {
		return galleryRepository.delete(no) == 1;
		
	}
	
}
