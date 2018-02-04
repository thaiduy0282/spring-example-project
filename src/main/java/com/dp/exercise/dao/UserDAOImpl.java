package com.dp.exercise.dao;

import java.util.List;

import com.dp.exercise.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class).list();

		return listUser;
	}

	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User findById(int id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

}
