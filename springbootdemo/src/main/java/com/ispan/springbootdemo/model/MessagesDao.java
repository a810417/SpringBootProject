package com.ispan.springbootdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesDao extends JpaRepository<Messages, Integer> {
	public Messages findFirstByOrderByAddedDesc();
	
	
	}
