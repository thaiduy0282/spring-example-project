package com.dp.exercise.dao;

import java.util.List;

import com.dp.exercise.model.User;

public interface UserDAO {

	public List<User> list();

	public void save(User user);

	public User findById(int id);

	public void delete(User user);

}
