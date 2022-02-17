package com.example.AnkushDemo.userData;
import java.util.*;

import org.springframework.stereotype.Component;
@Component
public class UserDao {
	private static List<UserModel> user=new ArrayList<>();
	private int userCount=3;
	static {
		user.add(new UserModel(1,"Ankush",new Date()));
		user.add(new UserModel(2,"Aditya",new Date()));
		user.add(new UserModel(3, "Karun", new Date()));
	}
	public List<UserModel> findAll(){
		return user;
	}
	public UserModel findOne(int id) {
		for(UserModel users:user) {
			if(users.getId()==id) {
				return users;
			}
		}
		return null;
	}
	public UserModel saveUser(UserModel users) {
		if(users.getId()==0) {
			users.setId(++userCount);
		}
		user.add(users);
		return users;
	}
	public UserModel deleteUser(int id) {
		Iterator<UserModel> itr=user.iterator();
		while(itr.hasNext()) {
			UserModel users=itr.next();
			if(users.getId()==id) {
				user.remove(id);
				return users;
			}
		}
//		throw new UserNotFoundException("User id is not found");
		return null;
	}
}
