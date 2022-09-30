package com.ispan.springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.springbootdemo.dto.MessageDto;
import com.ispan.springbootdemo.model.Messages;
import com.ispan.springbootdemo.service.MessageService;

@Controller
public class MessagesController {

	@Autowired
	private MessageService mService;

	@GetMapping("/messages/add")
	public String addMsg(Model model) {

		Messages m1 = new Messages();

		model.addAttribute("messages", m1);

		Messages lastestMsg = mService.findLastest();

		model.addAttribute("lastestMsg", lastestMsg);

		return "messages/addMessagePage";
	}

	@PostMapping("/messages/post")
	public String postMsg(@ModelAttribute(name = "messages") Messages msg, Model model) {

		mService.insert(msg);

		Messages m1 = new Messages();

		model.addAttribute("messages", m1);

		Messages lastestMsg = mService.findLastest();

		model.addAttribute("lastestMsg", lastestMsg);

		return "messages/addMessagePage";
	}

	@GetMapping("/messages/page")
	public String showMessages(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber, Model m) {
		Page<Messages> page = mService.findByPage(pageNumber);
		m.addAttribute("page", page);

		return "messages/showMessages";
	}

	// @PathVariable
//	@GetMapping("/messages/edit/{id}")
//	public String editMessage(@PathVariable(name="id") Integer id, Model m) {
//		
//		Messages m1 = mService.findById(id);
//		
//		m.addAttribute("messages", m1);
//		
//		return "messages/editMessage";
//	}

	// @RequestParam
	@GetMapping("/messages/edit")
	public String editMessage(@RequestParam(name = "id") Integer id, Model model) {

		Messages m1 = mService.findById(id);

		model.addAttribute("messages", m1);

		return "messages/editMessage";
	}

	@PostMapping("/messages/edit")
	public String editMessagePost(@ModelAttribute(name = "messages") Messages msg) {
		mService.insert(msg);

		return "redirect:/messages/page";
	}

	@GetMapping("/messages/del/{id}")
	public String deleteMessage(@PathVariable(name = "id") Integer id) {
		mService.deleteById(id);
		return "redirect:/messages/page";
	}
	
	@ResponseBody
	@PostMapping("/messages/api/post")
	public List<Messages> postMessagesApi(@RequestBody MessageDto dto) {
		 String userInput = dto.getInputText();
		 
		 // ... => 可設計過濾取得資料的邏輯
		 
		 // 將輸入資料塞入新的 Message 物件
		 Messages newMsg = new Messages();
		 newMsg.setText(userInput);
		 
		 mService.insert(newMsg);
		 
		 Page<Messages> page = mService.findByPage(1); // 取得第一頁的資料(一頁設定 3 筆)
		 List<Messages> list = page.getContent(); // 將取得的資料串中的內容取出(以 list 取出)
		 
		 return list;
	}
	
	@GetMapping("/messages/ajax")
	public String getAjaxPage() {
		return "/messages/ajax-message";
	}

}
