package com.ispan.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.springbootdemo.model.GoodPhoto;
import com.ispan.springbootdemo.model.GoodPhotoDao;

@Service
public class GoodPhotoService {
	
	@Autowired
	private GoodPhotoDao gDao;
	
	public GoodPhoto insert(GoodPhoto gp) {
		return gDao.save(gp);
		
	}
	
	public GoodPhoto getPhotoById(Integer id) {
		Optional<GoodPhoto> optional = gDao.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<GoodPhoto> listGoodPhoto(){
		return gDao.findAll();
	}

}
