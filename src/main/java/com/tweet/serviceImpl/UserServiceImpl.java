package com.tweet.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Image;
import com.tweet.model.User;
import com.tweet.repository.UserRepository;
import com.tweet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Object saveData(User user) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User userName = userRepository.findByUserName(user.getUserName());
		if (userName != null) {
			map.put("status", 400);
			map.put("message", "user name should be unique !");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}

		User emailId = userRepository.findByEmailId(user.getEmailId());
		if (emailId != null) {
			map.put("status", 400);
			map.put("message", "user should have unique email Id !");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}
		userRepository.save(user);
		map.put("status", 200);
		map.put("message", "Data Saved Successfully");
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

	@Override
	public Object login(String userName, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userRepository.findByUserNameAndPassword(userName, password);
		if (user == null) {
			map.put("status", 400);
			map.put("message", "Invalid Username or Password");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		} else {
			map.put("status", 200);
			map.put("message", "Login Successfull");
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
	}
	
	@Override
	public Object getAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public Object searchByUserName(String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user=userRepository.findByUserName(userName);
		if(user == null) {
			map.put("status", 400);
			map.put("message", "Username not found!");
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}else
		return userRepository.findByUserName(userName);
	}
	
	@Override
	public String creatingLocatinForImageFile(byte[] image,String imageName) throws Exception {
		Image img=Image.getInstance(image);
		String location="C:/Users/USER/Desktop/Profile Images/";
		Path newFile=Paths.get(location+imageName);
		Files.createDirectories(newFile.getParent());
		Files.write(newFile, image);
		return newFile.toAbsolutePath().toString();
	}
	
	@Override
	public FileSystemResource findInFileSystem(String location) {
		return new FileSystemResource(Paths.get(location));
	}
}
