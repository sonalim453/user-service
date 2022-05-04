package com.tweet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tweet.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
 public User findByUserName(String userName);
 public User findByEmailId(String emailId);
 
 public User findByUserNameAndPassword(String userName,String password);
 }
