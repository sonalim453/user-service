package com.tweet.service;

import java.io.IOException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.tweet.model.User;

@Service
public interface UserService {
	public Object saveData(User user) throws Exception;
	public Object login(String userName,String password);
	public Object getAllUsers();
	public Object searchByUserName(String userName);
	public String creatingLocatinForImageFile(byte[] image,String imageName) throws Exception;
	public FileSystemResource findInFileSystem(String location);
}
