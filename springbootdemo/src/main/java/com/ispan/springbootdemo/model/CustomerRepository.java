package com.ispan.springbootdemo.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// 根據參數位置(第幾個問號對應下方第幾個參數)
	@Query(value = "from Customer where name = ?1")
	Customer findCustomerByName(String name);

	// 用 @Param 做對應 -> :name 對應 @Param("name")
	@Query("from Customer where name = :name")
	Customer findCustomerByName2(@Param("name") String name);

	// 原生 SQL 語法
	@Query(value = "select * from Customer where name = :name", nativeQuery = true)
	Customer findCustomerByName3(@Param("name") String name);

	// 原生SQL語法(List)
	@Query(value = "select * from Customer where name = :name", nativeQuery = true)
	List<Customer> findCustomerByNameList(@Param("name") String name);

	// 原生 SQL 語法刪除
	@Transactional
	@Modifying
	@Query(value = "Delete from Customer where id = :id", nativeQuery = true)
	void delCustomerById(@Param("id") Integer id);
	
	// Spring 內建自動搭配語法(填入自設的 Java 屬性)
	List<Customer> findByLevelOrderById(Integer level);

}
