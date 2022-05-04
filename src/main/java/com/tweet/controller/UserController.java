package com.tweet.controller;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfTemplate;
import com.tweet.model.User;
import com.tweet.service.UserService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Object saveData(@RequestBody User user) throws Exception{
		return userService.saveData(user);
	}
	
	@GetMapping("/login")
	public Object login(@RequestParam String userName, @RequestParam String password) {
		return userService.login(userName, password);
	}
	
	@GetMapping("/users/all")
	public Object getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/search/username")
	public Object searchByUserName(@RequestParam String userName) {
		return userService.searchByUserName(userName);
	} 
	
	@PostMapping("/upload")
	public Object uploadingImage(@RequestPart MultipartFile images,@RequestParam String imageName) throws Exception {
		return userService.creatingLocatinForImageFile(images.getBytes(), imageName);
	}
	
	@GetMapping("/find")
	public FileSystemResource findInFileSystem(@RequestParam String location) throws BadElementException {
		return userService.findInFileSystem(location);
	}
}
