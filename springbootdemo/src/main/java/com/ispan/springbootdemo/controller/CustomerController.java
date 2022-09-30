package com.ispan.springbootdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ispan.springbootdemo.model.Customer;
import com.ispan.springbootdemo.model.CustomerRepository;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository cDao;

	@ResponseBody
	@GetMapping(path = "/customer/insert")
	public Customer insert() {
		Customer c1 = new Customer();
		c1.setName("廖老大");
		c1.setLevel(5);

		Customer resCus = cDao.save(c1);
		return resCus;
	}

	@ResponseBody
	@PostMapping(path = "/customer/insert2")
	public Customer insert2(@RequestBody Customer cus) {
		return cDao.save(cus);
	}

	@ResponseBody
	@PostMapping(path = "/customer/insert3")
	public List<Customer> insert3(@RequestBody List<Customer> reqList) {
		return cDao.saveAll(reqList);
	}

	@ResponseBody
	@GetMapping(path = "/customer/{id}")
	public Customer findByID(@PathVariable Integer id) {
		Optional<Customer> optional = cDao.findById(id);

		if (optional.isPresent()) {
			return optional.get(); // optional.get() 取得 Customer 物件
		}

		Customer cus = new Customer();
		cus.setName("Nothing!!!");
		return cus;
	}

	@ResponseBody
	@GetMapping(path = "/customer/all")
	public List<Customer> findAllCustomer() {
		return cDao.findAll();
	}

	@ResponseBody
	@GetMapping(path = "/customer/delete")
	public String deleteCustomerById(@RequestParam Integer id) {

		try {
			cDao.deleteById(id);
		} catch (Exception e) {
			return "沒有這筆資料";
		}

		return "做了一個刪除的動作";
	}

	@ResponseBody
	@GetMapping("/customer/page/{pageNumber}")
	public List<Customer> findByPage(@PathVariable Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 3, Sort.Direction.ASC, "id");
		Page<Customer> page = cDao.findAll(pgb);
		List<Customer> list = page.getContent();
		return list;
	}

	@ResponseBody
	@GetMapping("/customer/name")
	public Customer findCustomerByName(@RequestParam String name) {
//		return cDao.findCustomerByName(name);
//		return cDao.findCustomerByName2(name);
		return cDao.findCustomerByName3(name);
	}

	@ResponseBody
	@GetMapping("/customer/nameList")
	public List<Customer> findCustomerByNameList(@RequestParam String name) {
		return cDao.findCustomerByNameList(name);
	}

	@ResponseBody
	@GetMapping("/customer/delete/{id}")
	public boolean deleteByid(@PathVariable Integer id) {
		cDao.delCustomerById(id);
		return true;
	}
	
	@ResponseBody
	@GetMapping("/customer/level/{level}")
	public List<Customer> findByLevelOrderById(@PathVariable Integer level){
		return cDao.findByLevelOrderById(level);
		
	}

}
