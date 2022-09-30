package com.ispan.springbootdemo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ispan.springbootdemo.model.Messages;
import com.ispan.springbootdemo.model.MessagesDao;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessagesDao mDao;

	public void insert(Messages msg) {
		mDao.save(msg);
	}

	public Messages findById(Integer id) {
		Optional<Messages> optional = mDao.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void deleteById(Integer id) {
		mDao.deleteById(id);
	}

	public void deleteByEntity(Messages msg) {
		mDao.delete(msg);
	}

	public List<Messages> findAll() {
		return mDao.findAll();
	}

	public Page<Messages> findByPage(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.DESC, "added");
		Page<Messages> page = mDao.findAll(pgb);

		return page;
	}

	public Messages findLastest() {
		return mDao.findFirstByOrderByAddedDesc();
	}
	

}
