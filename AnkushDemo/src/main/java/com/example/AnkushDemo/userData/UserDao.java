package com.example.AnkushDemo.userData;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class UserDao {
	private static List<UserModel> user=new ArrayList<>();
	static {
		user.add(new UserModel(1,"Ankush",new Date()));
		user.add(new UserModel(2,"Aditya",new Date()));
		user.add(new UserModel(3, "Karun", new Date()));
	}
	public List<UserModel> findAll(){
		return user;
	}
}
