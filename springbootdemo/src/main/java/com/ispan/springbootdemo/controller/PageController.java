package com.ispan.springbootdemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.springbootdemo.model.GoodPhoto;
import com.ispan.springbootdemo.service.GoodPhotoService;

@Controller
public class PageController {
	
	@Autowired
	private GoodPhotoService gpService;
	
	@GetMapping("/")
	public String goHome() {
		return "index";
	}
	
		
	@GetMapping("/newPhoto")
	public String newPhotoPage() {
		return "goodphoto/uploadPage";
	}
	
	@PostMapping("/uploadPhoto")
	public String postPhotoAction(@RequestParam(name="photoName", required = true) String name,
			                      @RequestParam(name="file") MultipartFile file,
			                      RedirectAttributes redirectAttributes) {
		GoodPhoto goodPhoto = new GoodPhoto();
		
		
		try {
			goodPhoto.setPhoto_name(name);
			goodPhoto.setPhoto_file(file.getBytes());
			
			gpService.insert(goodPhoto);
			
			return "redirect:/";
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMsg", "上傳失敗，請重新上傳");
			return "redirect:newPhoto";
		}
	}
	
	@GetMapping("/listGoodPhoto")
	public String getAllPhoto(Model model) {
		List<GoodPhoto> list = gpService.listGoodPhoto();
		model.addAttribute("photoList", list);
		
		return "goodphoto/listPhoto";
	}
	
	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<byte[]> downloadImage(@PathVariable Integer id){
		GoodPhoto photo1 = gpService.getPhotoById(id);
		byte[] photoFile = photo1.getPhoto_file();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoFile, headers, HttpStatus.OK);
	}

}